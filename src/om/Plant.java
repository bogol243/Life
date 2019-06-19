package om;

public class Plant extends Life implements Food {
	
	private String name;

	public Plant(int id, String name, double weight) {
		super(id,name,weight);
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
