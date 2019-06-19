package om;

import java.io.Serializable;

public class Life implements Serializable{
	protected int id;
	protected double weight;
	protected String name;
	
	public Life() {
		this.id = 0;			//id
		this.name = "default";		//name
		this.weight = 100;	//mass
	}
	
	//id default constructor
	public Life(int id){
		this.id = id;			//id
		this.name = "default";		//name
		this.weight = 100;	//mass
	}
	
	public Life(int id, String name, double weight){
		this.id = id;			//id
		this.name = name;		//name
		this.weight = weight;	//mass
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInfo(){
		return	" id: " + Integer.toString(id) +
				"\n name: " + name +
				"\n weight: " + Double.toString(weight);
	}
}
