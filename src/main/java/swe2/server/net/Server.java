package swe2.server.net;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swe2.shared.net.ConnectionDefaults;


public class Server {

	private static final Logger logger = LoggerFactory.getLogger(Server.class);
	
	public static void main(String[] args) {
		final int port = args.length > 0 ?
			Integer.parseInt(args[0]) : ConnectionDefaults.PORT;
		new Server(port);
	}
	
	public Server(int port) {
		// try-with-resources ensures socket will always close
		try (ServerSocket welcomeSocket = new ServerSocket(port);) {
			logger.info("Server started.");
			while(true) {
				try {
					final Socket dataSocket = welcomeSocket.accept();
					final InetAddress remoteIp = dataSocket.getInetAddress();
					final int remotePort = dataSocket.getPort();
					logger.info("Created connection to " + remoteIp + ":" + remotePort);
					
					final Thread streamThread = new Thread(new ServerConnection(dataSocket));
					streamThread.start();
				} catch(IOException e) {
					logger.error("Creating connection to client failed!", e);
				}
			}
		} catch (IOException e) {
			logger.error("Server start failed!", e);
		}		
	}
}