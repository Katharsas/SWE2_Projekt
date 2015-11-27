package swe2.server;

public class Main{

	public static void main( String[] args ){
		
		String port = ( args.length > 0 ) ? args[0] : "6666";
		
		try{
			new Server( Integer.parseInt(port) );
		}catch( Exception e ){ e.printStackTrace(); }
	}

}