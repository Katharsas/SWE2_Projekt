package swe2.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swe2.shared.net.ConnectionDefaults;

public class ClientMain{

	private static final Logger logger = LoggerFactory.getLogger(ClientMain.class);
	
	public static void main(String[] args) {
	
		final String serverIp = args.length > 0 ?
				args[0] : ConnectionDefaults.IP;
				
		final int serverPort = args.length > 1 ?
				Integer.parseInt(args[1]) : ConnectionDefaults.PORT;
				
		try(ClientConnection client = new ClientConnection(serverIp, serverPort)) {
			client.connect();
		} catch(IOException e) {
			logger.error("Creating a new client connection failed!", e);
		}
	}
}