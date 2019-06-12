package om;

import java.io.*;
import java.util.HashSet;

public class World extends HashSet<Life>{
	public enum Type{
		HERBIVORE,
		PREDATOR,
		PLANT
	}
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
			
			oos.writeObject(this);
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
		if(path == "default_path") path = filename;
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.addAll((World)ois.readObject());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// add method is here as the member of superclass
	
	//get by id
	public Life get(int id){
		Life result = null;
		for(Life l:this){
			if(l.getId()==id){
				result = l;
				break;
			}
		}
		return result;
	}
	
	public boolean remove(int id){
		for(Life l:this){
			if(l.getId()==id){
				this.remove(l);
					return true;
			}
		}
		return false;
	}
	
	public HashSet<Herbivore> getHerbivores(){
		HashSet<Herbivore> result = new HashSet<Herbivore>();
		for(Life l:this){
			if(l instanceof Herbivore){
				result.add((Herbivore)l);
			}
		}
		return result;
	}
	
	public HashSet<Predator> getPredators(){
		HashSet<Predator> result = new HashSet<Predator>();
		for(Life l:this){
			if(l instanceof Predator){
				result.add((Predator)l);
			}
		}
		return result;
	}
	
	public HashSet<Plant> getPlants(){
		HashSet<Plant> result = new HashSet<Plant>();
		for(Life l:this){
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
	
	// UPDATE METHODS DONE
	void update(Life life) throws Exception{
		if(!this.contains(life)){
			throw new Exception("There's no such object in this World");
		}
		this.add(life);
		return;
	}
	
	@Override
	public String toString(){
		String res = "";
		for(Life l:this){
			res = res + l.getInfo();
			res = res + "\n";
		}
		return res;
	}
}
