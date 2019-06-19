package main;
import net.*;
import om.*;
import ui.*;

public class Start {
	public static void main(String agrs[]){
		MainControl.startServer(1500);
	}
	
	
	
    public static void loadSaveTest(){
    	World world = new World();
		world.create(World.Type.HERBIVORE);
		world.create(World.Type.PLANT);
		world.create(World.Type.PREDATOR);
		
		
		world.saveToFile();
		
		World newWorld = new World("default_path");
		System.out.println(world);
		System.out.println(newWorld);
		ServerFrame sf = new ServerFrame();
    }
}

