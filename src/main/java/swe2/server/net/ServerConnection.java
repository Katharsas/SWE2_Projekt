package swe2.server.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swe2.server.data.DataAccess;
import swe2.server.data.DataBase;
import swe2.shared.model.Combustion;
import swe2.shared.model.Delivery;
import swe2.shared.model.User;
import swe2.shared.net.Credentials;
import swe2.shared.net.DataPackage;
import swe2.shared.net.RequestType;

public class ServerConnection implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(ServerConnection.class);
	
	private final ObjectInputStream reader;
	private final ObjectOutputStream writer;
	private final DataAccess data = DataBase.getInstance();
	
	public ServerConnection(Socket socket) {
		try {
			writer = new ObjectOutputStream(socket.getOutputStream());
			reader = new ObjectInputStream(socket.getInputStream());
			logger.info("Streams created");
			
		} catch (final IOException e) {
			throw new IllegalStateException("Streams could not be created", e);
		}
	}

	@Override
	public void run() {
		//Do something
		handle();
		logger.info("Connection closing...");
	}
	
	public void handle(){
		// Paket entgegennehmen;
		DataPackage inbox = null;
		try {
			inbox = (DataPackage) reader.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Paket oeffnen
		RequestType req = inbox.getRequestType();
		// Entscheiden was gemacht werden soll
		switch (req) {
		case GET_DELIVERY:
			sendBack(new DataPackage(RequestType.DATA, (Serializable) data.getDeliveries()));
			break;
		case GET_COMBUSTION:
			sendBack(new DataPackage(RequestType.DATA, (Serializable) data.getCombustions()));
			break;
		case GET_WASTESTORAGE:
			sendBack(new DataPackage(RequestType.DATA, data.getStorage()));
			break;
		case VALIDATION:
			Credentials cred = (Credentials) inbox.getData();
			// System.out.println( cred.getUserId() + " " +
			// cred.getPassword() );
			User user = data.authenticate(cred.getUserId(), cred.getPassword(), cred.getType());
			if (user != null)
				sendBack(new DataPackage(RequestType.GRANTED, user));
			else
				sendBack(new DataPackage(RequestType.ERROR, "Inkorrekte Eingabe!"));
			break;
		case PUT_COMBUSTION:
			if (data.addCombustion((Combustion) inbox.getData()))
				sendBack(new DataPackage(RequestType.SUCCESS, "Combustionbericht wurde gespeichert"));
			else
				sendBack(new DataPackage(RequestType.ERROR, "Combustionbericht konnte nicht gespeichert werden"));
			break;
		case PUT_DELIVERY:
			if (data.addDelivery((Delivery) inbox.getData()))
				sendBack(new DataPackage(RequestType.SUCCESS, "Deliverybericht wurde gespeichert"));
			else
				sendBack(new DataPackage(RequestType.ERROR, "Deliverybericht konnte nicht gespeichert werden"));
			break;
		default:
			sendBack(new DataPackage(RequestType.ERROR, "Der Server ist verwirrt"));
			break;
		}
	}
	
	/**
	 * Daten werden zurueckgeschickt und Verbindung beendet.
	 * Es wird immer etwas zurueckgeschickt, jede Verbindung endet mit
	 * dieser Methode.
	 */
	public void sendBack(Serializable toSend) {
		try {
			writer.writeObject(toSend);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}