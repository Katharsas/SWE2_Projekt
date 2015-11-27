import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Client{
	
	ObjectInputStream reader; ObjectOutputStream writer;
	String serverName; int serverPort;

	public Client( String serverName, int serverPort ){
	
		this.serverName = serverName;
		this.serverPort = serverPort;
	
	}//Constructor------------------------
	
	public void connect() throws Exception{
	
	
		Socket socket = new Socket( serverName, serverPort );
		System.out.println( "Verbindung erstellt" );
		
		reader = new ObjectInputStream( socket.getInputStream() );
		writer = new ObjectOutputStream( socket.getOutputStream() );
		System.out.println( "Stream erstellt" );
		
	}//connect
	


}//class