package om;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class World extends HashSet<Life>{
	public enum Type{
		HERBIVORE,
		PREDATOR,
		PLANT
	}
	private static World storage = new World();

    public static World getInstance() {
        if (storage == null)
            storage = new World();

        return storage;
    }
	public static final String DEFAULT_PATH = "default_path";
	private String filename = "Life.txt";  //default filename
	private int idCount = 0;
	
	public void setFileName(String name){
		filename = name;
	}
	
	//save to file
	public boolean saveToFile(){
		try {
			FileOutputStream fos = new FileOutputStream(filename); 
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(storage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//default constructor
	public World(){
		super();
	}
	
	//load from file constructor
	public World(String path){
		load(path);
	}
	
	public void load(String path) {
		if(path == DEFAULT_PATH) path = filename;
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			storage.removeAll();
			storage.addAll((World)ois.readObject());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void removeAll() {
		HashSet<Life> c = (HashSet<Life>)storage.clone();
		storage.removeAll(c);
	}
	
	// add method is here as the member of superclass
	
	//get by id
	public Life get(int id){
		Life result = null;
		for(Life l:storage){
			if(l.getId()==id){
				result = l;
				break;
			}
		}
		return result;
	}
	
	public boolean remove(int id){
		for(Life l:storage){
			if(l.getId()==id){
				storage.remove(l);
					return true;
			}
		}
		return false;
	}
	
	public HashSet<Herbivore> getHerbivores(){
		HashSet<Herbivore> result = new HashSet<Herbivore>();
		for(Life l:storage){
			if(l instanceof Herbivore){
				result.add((Herbivore)l);
			}
		}
		return result;
	}
	
	public HashSet<Predator> getPredators(){
		HashSet<Predator> result = new HashSet<Predator>();
		for(Life l:storage){
			if(l instanceof Predator){
				result.add((Predator)l);
			}
		}
		return result;
	}
	
	public HashSet<Plant> getPlants(){
		HashSet<Plant> result = new HashSet<Plant>();
		for(Life l:storage){
			if(l instanceof Plant){
				result.add((Plant)l);
			}
		}
		return result;
	}
	
	// CREATE METHODS
	
	
	public boolean create(Life newBeing){
		newBeing.setId(idCount++);
		add(newBeing);
		return true;
	}
	
	public boolean create(Type type){
		Life newBeing;
		switch(type){
			case HERBIVORE:
				newBeing = new Herbivore(idCount++,"default",100);
				break;
			case PREDATOR:
				newBeing = new Predator(idCount++,"default",100);
				break;
			case PLANT:
				newBeing = new Plant(idCount++,"default",100);
				break;
			default:
				newBeing = new Life(idCount++,"default",100);
		}
		add(newBeing);
		return true;
	}
	
	public Plant getRandomPlant() {
		Random ran = new Random();
		return (getPlants().toArray(new Plant[0])[ran.nextInt(getPlants().size())]);
	}
	
	public Herbivore getRandomHerbivore() {
		Random ran = new Random();
		return (getHerbivores().toArray(new Herbivore[0])[ran.nextInt(getHerbivores().size())]);
	}
	
	// UPDATE METHODS DONE
	void update(Life life) throws Exception{
		if(!storage.contains(life)){
			throw new Exception("There's no such object in storage World");
		}
		storage.add(life);
		return;
	}
	
	@Override
	public String toString(){
		String res = "";
		for(Life l:storage){
			res = res + l.getInfo();
			res = res + "\n";
		}
		return res;
	}
}
