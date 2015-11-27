package swe2.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			String s;
			
			try{
				s = (String) reader.readObject();
				System.out.println(s);
			} catch(final Exception e) {
				e.printStackTrace();
			}
		}
	}
}