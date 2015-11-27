public class Main{

	public static void main( String[] args ){
		
		try{
			new Server( 6666 );
		}catch( Exception e ){ e.printStackTrace(); }
	}

}