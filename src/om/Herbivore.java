package om;

public class Herbivore extends Animal implements Food {

	public Herbivore(int id, String name, double weight){
		super(id, name, weight);
	}

	public void eat(Food food) throws Exception{
		// питаться могут только живые особи
		if (!this.getAlive())
			throw new IllegalStateException("Only living animals can eat");
		//Травоядные едят только траву
		if(! (food instanceof Plant))
			throw new IllegalArgumentException("Herbivores can eat only grass");
		
		this.setWeight(this.getWeight() + ((Plant) food).getWeight()); 	// добавляем вес съеденной травы 
																		// к весу животного
	}
}
