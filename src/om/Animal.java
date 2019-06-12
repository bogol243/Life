package om;

public abstract class Animal extends Life{
 
	private boolean isAlive;
	
	public Animal(int id, String name, double weight) throws  IllegalArgumentException{
		super(id,name,weight);
		if(weight>10000) throw new IllegalArgumentException("This animal is overweighted");
		this.isAlive = true;	//состояние
	}

	public void die() throws Exception{
		if(!isAlive){
			throw new IllegalStateException("You cannot die if you are already dead");
		}
		isAlive = false;
	}

	public abstract void eat(Food food) throws Exception;

	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean getAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
