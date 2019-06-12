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
		Life life = protocol.getData();
		try {
			switch (protocol.getCommand()) {
				case Protocol.ADD_LIFE:
					world.create(life);
					break;
				case Protocol.DELETE_LIFE:
					world.remove(life.getId());
					break;
				case Protocol.EAT:
					Herbivore h = (Herbivore) life;
					h.eat(world.getRandomPlant());
					break;
				case Protocol.HUNT:
					Predator p = (Predator) life;
					p.eat(world.getRandomHerbivore());
					break;
				case Protocol.LOAD_WORLD:
					world.load(protocol.getMessage());
					break;
				case Protocol.SAVE_WORLD:
					world.saveToFile();
					break;
				default:
					break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//perform action
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
