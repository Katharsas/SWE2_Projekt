package swe2.client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import swe2.shared.model.DataPackage;
import swe2.shared.model.RequestType;

public class Client{
	
	ObjectInputStream reader; ObjectOutputStream writer;
	String serverName; int serverPort;
	Socket socket;
        
        public Client(){
            this.serverName = "localhost";
            this.serverPort = 6666;
        }

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
		//writer.writeObject( "Test" );
		
	}//connect
	
	public void close() throws Exception{
		socket.close();
	}
        
        /*-----------------------------------------
        * MAIN METHODS
        * ---------------------------------------*/
        
        public Serializable getCombustions() throws Exception{
            
           writer.writeObject( new DataPackage( RequestType.GET_COMBUSTION, "Need Combustion" ) );
           DataPackage returnedData = (DataPackage) reader.readObject();
           return (Serializable) returnedData.getData();	
	}
        
        public Serializable getDeliveries() throws Exception{
            
           writer.writeObject( new DataPackage( RequestType.GET_DELIVERY, "Need Delivery" ) );
           DataPackage returnedData = (DataPackage) reader.readObject();
           return (Serializable) returnedData.getData();	
	}
        
        public Serializable getWasteStorage() throws Exception{
            
           writer.writeObject( new DataPackage( RequestType.GET_WASTESTORAGE, "Need WasteStorage" ) );
           DataPackage returnedData = (DataPackage) reader.readObject();
           return (Serializable) returnedData.getData();	
	}
        
	public Serializable getAccess( Serializable data ) throws Exception{
           writer.writeObject( new DataPackage( RequestType.VALIDATION, data ) );
           DataPackage returnedData = (DataPackage) reader.readObject();
           if(  returnedData.getRequestType() == RequestType.GRANTED )
               return (Serializable) returnedData.getData();
           else
               System.out.println( returnedData.getData() );
               return null;	
	}
        
	public String saveCombustion( Serializable data ) throws Exception{
            
           writer.writeObject( new DataPackage( RequestType.PUT_COMBUSTION, data ) );
           DataPackage returnedData = (DataPackage) reader.readObject();
           return (String) returnedData.getData();	
	}
        
        public String saveDelivery( Serializable data ) throws Exception{
            
           writer.writeObject( new DataPackage( RequestType.PUT_DELIVERY, data ) );
           DataPackage returnedData = (DataPackage) reader.readObject();
           return (String) returnedData.getData();	
	}
        


}//class