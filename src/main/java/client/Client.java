package client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Client{
	
	ObjectInputStream reader; ObjectOutputStream writer;
	String serverName; int serverPort;
	Socket socket;

	public Client( String serverName, int serverPort ){
	
		this.serverName = serverName;
		this.serverPort = serverPort;
	
	}//Constructor------------------------
	
	public void connect() throws Exception{
	
	
		socket = new Socket( serverName, serverPort );
		System.out.println( "Verbindung erstellt" );
		
		writer = new ObjectOutputStream( socket.getOutputStream() );
		reader = new ObjectInputStream( socket.getInputStream() );
		
		System.out.println( "Stream erstellt" );
		writer.writeObject( "Test" );
		
	}//connect
	
	public void close() throws Exception{
	
		socket.close();
	
	}
	
	public void doStuff() throws Exception{
		writer.writeObject( "Test" );
	}

}//class