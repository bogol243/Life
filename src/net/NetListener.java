package net;

import java.net.*;
import java.io.*;

public class NetListener implements Runnable{
	private ServerSocket serverSocket;
	
	public NetListener (int port) throws IOException{
		serverSocket = new ServerSocket(port);
	}
	
	@Override
	public void run(){
		while(true) {
			try {
				Socket client = serverSocket.accept();
				new Thread(new ClientHandler(client)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
