package net;

import java.io.*;
import java.net.*;

import om.*;

public class ClientHandler implements Runnable{
	private Protocol protocol;
	private Socket socket;
	private World world;
	private ObjectInputStream input;
	
	public ClientHandler (Socket socket) throws IOException{
		this.socket = socket;
		if(socket.isConnected()) {
			input = new ObjectInputStream(socket.getInputStream());
		}
	}
	
	@Override
	public void run() {
		//read message
		protocol = readMessage();
		switch (protocol.getCommand()) {
			case Protocol.ADD_LIFE:
				addLife(protocol);
				break;
			case Protocol.DELETE_LIFE:
				deleteLife(protocol);
				break;
			case Protocol.EAT:
				
				break;
			case Protocol.HUNT:
				break;
			case Protocol.LOAD_WORLD:
				break;
			case Protocol.SAVE_WORLD:
				break;
			default:
				break;
		}
		
		//perform action
	}
	
	private void deleteLife(Protocol protocol) {
		Life life = protocol.getData();
		world.remove(life.getId());
	}
	
	private void addLife(Protocol protocol) {
		Life life = protocol.getData();
		world.create(life);
	}
	
	private Protocol readMessage() {
		Protocol data = null;
		try {
			if(input.available()>0) {
				data = (Protocol)input.readObject();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}
}
