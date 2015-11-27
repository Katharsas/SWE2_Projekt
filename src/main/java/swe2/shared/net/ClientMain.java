public class ClientMain{

	public static void main( String [] args ){
	
		String serverName = ( args.length > 0 ) ? args[0] : "localhost";
		String serverPort = ( args.length > 1 ) ? args[1] : "6666";
	
		try{
			new Client( serverName, Integer.parseInt(serverPort) ).connect();
		}catch( Exception e ){ e.printStackTrace(); }
	
	
	}
	
}