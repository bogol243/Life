package main;

import java.io.*;

import net.*;

public class MainControl {
	private static NetListener netListener;
	
	public static void startServer(int port) {
			try {
				netListener = new NetListener(port);
				new Thread(netListener).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
}
