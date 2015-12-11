package swe2.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import swe2.shared.data.*;
import swe2.shared.model.Combustion;
import swe2.shared.model.Credentials;
import swe2.shared.model.DataPackage;
import swe2.shared.model.Delivery;
import swe2.shared.model.RequestType;
import swe2.shared.model.User;

/**
 *TABLE_OF_CONTENTS
 *Construktor
 * 
 * ServerConnection
 * void run()
 * void handle()
 * void sendBack( Serializable )
 */

public class Server{

	/*
	INTERFACE dataToSave;
	INTERFACE dataToSend
	*/
	
	public Server(int port) throws Exception {
		
		final ServerSocket welcomeSocket = new ServerSocket( port );
		System.out.println( "Server gestartet." );
		
		while(true){
			final Socket dataSocket = welcomeSocket.accept();
			final InetAddress ipAddress = dataSocket.getInetAddress();
			System.out.printf( "Verbindung erstellt mit " + ipAddress );
			
			final Thread streamThread = new Thread(new ServerConnection(dataSocket));
			streamThread.start();
		}
	}
	
	public static class ServerConnection implements Runnable {
		
		final ObjectInputStream reader;
		final ObjectOutputStream writer;
                final static DataAccess data = new DataBase();
		
		public ServerConnection(Socket socket) {
			try {
				writer = new ObjectOutputStream(socket.getOutputStream());
				reader = new ObjectInputStream(socket.getInputStream());
				System.out.println("Streams erstellt");
				
			} catch (final IOException e) {
				throw new IllegalStateException("Streams could not be created", e);
			}
		}

		@Override
		public void run() {
			 //Do something
                        handle();
                        System.out.println("fertig");
		}
		
		public void handle(){
                    //Paket entgegennehmen;
                    DataPackage inbox = null;
                    try{
                        inbox = (DataPackage) reader.readObject();
                    }catch( Exception e){e.printStackTrace();}
                    
                    //Paket oeffnen
                    RequestType req = inbox.getRequestType();
                    //Entscheiden was gemacht werden soll
                    switch(req){
                        case GET_DELIVERY:
                            sendBack( new DataPackage( RequestType.DATA, (Serializable)data.getDeliveries() ) );
                            break;
                        case GET_COMBUSTION:
                            sendBack( new DataPackage( RequestType.DATA, (Serializable)data.getCombustions() ) );
                            break;
                        case GET_WASTESTORAGE:
                            sendBack( new DataPackage( RequestType.DATA, (Serializable)data.getStorage() ));
                            break;
                        case VALIDATION:
                            Credentials cred = (Credentials) inbox.getData();
                            System.out.println( cred.getUserId() + " " + cred.getPassword() );
                            User user = data.authenticate(cred.getUserId(), cred.getPassword(), cred.getType() );
                            if(user != null)
                                sendBack( new DataPackage( RequestType.GRANTED, user) );
                            else
                                sendBack( new DataPackage( RequestType.ERROR, "Inkorrekte Eingabe!" ) );
                            break;
                        case PUT_COMBUSTION:
                            if( data.addCombustion( (Combustion) inbox.getData() ) )
                                sendBack( new DataPackage( RequestType.SUCCESS, "Combustionbericht wurde gespeichert" ) );
                            else
                                sendBack( new DataPackage( RequestType.ERROR, "Combustionbericht konnte nicht gespeichert werden" ) );
                            break;
                        case PUT_DELIVERY:
                            if( data.addDelivery( (Delivery) inbox.getData() ) )
                                sendBack( new DataPackage( RequestType.SUCCESS, "Deliverybericht wurde gespeichert" ) );
                            else
                                sendBack( new DataPackage( RequestType.ERROR, "Deliverybericht konnte nicht gespeichert werden" ) );
                            break;
                        default:
                            sendBack( new DataPackage( RequestType.ERROR, "Der Server ist verwirrt" ) );
                            break;
                    
                    }
                    
                    
		}
                
                //Daten werden zurueckgeschickt und Verbindung beendet.
                //Es wird immer etwas zurueckgeschickt, jede Verbindung endet mit dieser Methode.
                public void sendBack( Serializable toSend ){
                    
                    try{
                        writer.writeObject( toSend );
                    }catch( IOException e ){ e.printStackTrace(); }
                }
	}
}