package swe2.client.net;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swe2.shared.net.ConnectionDefaults;
import swe2.shared.net.DataPackage;
import swe2.shared.net.RequestType;

public class ClientConnection implements Closeable {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientConnection.class);
	
	private final String serverIp;
	private final int serverPort;
	
	private Socket socket;
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
        
	public ClientConnection(){
	    this(ConnectionDefaults.IP, ConnectionDefaults.PORT);
	}

	public ClientConnection( String serverName, int serverPort ){
		this.serverIp = serverName;
		this.serverPort = serverPort;
	}
	
	public void connect() throws IOException {
		socket = new Socket( serverIp, serverPort );
		logger.info("Client connection to server created");
		
		writer = new ObjectOutputStream( socket.getOutputStream() );
		reader = new ObjectInputStream( socket.getInputStream() );
		logger.info("Streams created & connected");
	}
	
	@Override
	public void close() throws IOException {
		socket.close();
	}
        
        /*-----------------------------------------
        * MAIN METHODS
        * ---------------------------------------*/
        
	public Serializable getCombustions() throws Exception {

		writer.writeObject(new DataPackage(RequestType.GET_COMBUSTION, "Need Combustion"));
		final DataPackage returnedData = (DataPackage) reader.readObject();
		return returnedData.getData();
	}

	public Serializable getDeliveries() throws Exception {

		writer.writeObject(new DataPackage(RequestType.GET_DELIVERY, "Need Delivery"));
		final DataPackage returnedData = (DataPackage) reader.readObject();
		return returnedData.getData();
	}

	public Serializable getWasteStorage() throws Exception {

		writer.writeObject(new DataPackage(RequestType.GET_WASTESTORAGE, "Need WasteStorage"));
		final DataPackage returnedData = (DataPackage) reader.readObject();
		return returnedData.getData();
	}

	public Serializable getAccess(Serializable data) throws Exception {
		writer.writeObject(new DataPackage(RequestType.VALIDATION, data));
		final DataPackage returnedData = (DataPackage) reader.readObject();
		if (returnedData.getRequestType() == RequestType.GRANTED)
			return returnedData.getData();
		else
			System.out.println(returnedData.getData());
		return null;
	}

	public String saveCombustion(Serializable data) throws Exception {

		writer.writeObject(new DataPackage(RequestType.PUT_COMBUSTION, data));
		final DataPackage returnedData = (DataPackage) reader.readObject();
		return (String) returnedData.getData();
	}

	public String saveDelivery(Serializable data) throws Exception {

		writer.writeObject(new DataPackage(RequestType.PUT_DELIVERY, data));
		final DataPackage returnedData = (DataPackage) reader.readObject();
		return (String) returnedData.getData();
	}
}//class