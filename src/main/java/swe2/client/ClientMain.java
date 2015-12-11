package swe2.client;

public class ClientMain{

	public static void main( String [] args ){
	
		String serverName = ( args.length > 0 ) ? args[0] : "localhost";
		String serverPort = ( args.length > 1 ) ? args[1] : "6666";
		Client client;
		
		try{
			client = new Client( serverName, Integer.parseInt(serverPort) );
			client.connect();

			client.close();
		}catch( Exception e ){ e.printStackTrace(); }
	
	
	}
	
}