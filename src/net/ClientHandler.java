package net;

import java.io.*;
import java.net.*;

import om.*;

public class ClientHandler implements Runnable{
	private Protocol protocol;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public ClientHandler (Socket socket) throws IOException{
		this.socket = socket;
		if(socket.isConnected()) {
			System.out.println("Client Connected");
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		}
	}
	
	@Override
	public void run() {
		handling:
		while(true) {
			//read message
			protocol = readMessage();
			if(protocol==null) {
				continue;
			}
				Life life = protocol.getData();
			// handle the commands
				World world = World.getInstance();
				switch (protocol.getCommand()) {
					case Protocol.ADD_LIFE:
						world.create(life);
						break;
					case Protocol.DELETE_LIFE:
						world.remove(life.getId());
						break;
					case Protocol.EAT:
						Herbivore h = (Herbivore) life;
					try {
						h.eat(world.getRandomPlant());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case Protocol.HUNT:
						Predator p = (Predator) life;
					try {
						p.eat(world.getRandomHerbivore());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case Protocol.LOAD_WORLD:
						//TODO world.load produces null pointer exception
						world.load(World.DEFAULT_PATH);
						System.out.println(protocol.getMessage());
						break;
					case Protocol.SAVE_WORLD:
						world.saveToFile();
						System.out.println(protocol.getMessage());
						break;
					case Protocol.UPDATE:
						writeBackUpdate();
						break;
					case Protocol.DISCONNECT:
						disconnectClient();
						break handling;
					default:
						break;
				}
			
			//perform action
		}
	}
	
	private void disconnectClient() {
		try {
			System.out.println("Client disconnected");
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		
		
	}
	
	private void writeBackUpdate() {
		//THE WORLD
		
		/*///TOKI WO TOMARE
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-degenerated catch block
			e.printStackTrace();
		}
		/*///SOSHITE TOKI WA UGOKIDASU
		
		ProtocolBackInfo pbi = new ProtocolBackInfo(World.getInstance());
		try {
			output.writeObject(pbi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Protocol readMessage() {
		Protocol data = null;
		try {
			data = (Protocol)input.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}
}
