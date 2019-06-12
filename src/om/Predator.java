package om;

public class Predator extends Animal {

	public Predator(int id, String name, double weight) {
		super(id, name, weight);
	}
	public void eat(Food food) throws Exception{
		//есть могут только живые особи
		if (!this.getAlive()) 
			throw new IllegalStateException("Only living animals can eat");
			// хищник ест только травоядных
		if ( !(food instanceof Herbivore))
			throw new IllegalArgumentException("Predator can eat only herbivores");
		
		Herbivore h = (Herbivore)food;	//Food object casted to a proper class
		
		//Жертва найдена, это живое травоядное. Начинается охота...
		if(!huntSuccess()){
			// Жертва убежала
			System.out.println("The prey, glorious "+h.getName()+", was lucky enough to run away.");
			return;
		}
		//Жертва съедена
		System.out.println("The "+this.getName()+" was lucky enough to hunt down that "+h.getName()+".");
		h.die();	//жертва умирает
		
		this.setWeight(this.getWeight() + h.getWeight()); 	// увеличиваем вес хищиника на
															// величину веса травоядного
		System.out.println(h.getWeight()+" killograms of flesh was successfully eaten");
		h.setWeight(0);	//животное было съедено полностью
		
	}

	private boolean huntSuccess(){
		return (Math.random() > 0.7);
	}
}
