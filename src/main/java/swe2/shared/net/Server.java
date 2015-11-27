import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *TABLE_OF_CONTENTS
 *Construktor
 *void handle()
 */

public class Server{

	/*
	INTERFACE dataToSave;
	INTERFACE dataToSend
	
	Database data;
	*/
	
	ObjectInputStream reader;
	ObjectOutputStream writer;
	
	public Server( int port ) throws Exception{
	
		ServerSocket welcomeSocket = new ServerSocket( port );
		System.out.println( "Server gestartet." );
		
		while(true){
		
			final Socket dataSocket = welcomeSocket.accept();
			InetAddress ipAddress = dataSocket.getInetAddress();
			
			System.out.printf( "Verbindung erstellt mit " + ipAddress );
			
			Thread streamThread = new Thread( new Runnable(){
			
				@Override
			    public void run(){
            
                    try{
                        reader = new ObjectInputStream( dataSocket.getInputStream() );
                        writer = new ObjectOutputStream( dataSocket.getOutputStream() );
                    }catch( IOException e ){ e.printStackTrace(); }
                    System.out.println( "Streams erstellt" );
                    
                    //Do something
                    handle();
                    System.out.println( "fertig" );
            	}
			} );
			
			
			streamThread.start();

		}//while true
	
	}//Constructor-----------------------------------------------

	public void handle(){
		System.out.println( "Doin' dirty" );
	}

}//class