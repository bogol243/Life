package net;

import java.io.Serializable;

import om.*;

public class ProtocolBackInfo implements Serializable {
	private Predator[] predatorsInfo;
	private Herbivore[] herbivoresInfo;
	private Plant[] plantsInfo;
	
	public ProtocolBackInfo(World world) {
		herbivoresInfo = world.getHerbivores().toArray(new Herbivore[0]);
		predatorsInfo = world.getPredators().toArray(new Predator[0]);
		plantsInfo = world.getPlants().toArray(new Plant[0]);
	}
	
	public Predator[] getPredatorsInfo() {
		return predatorsInfo;
	}

	public Herbivore[] getHerbivoresInfo() {
		return herbivoresInfo;
	}

	public Plant[] getPlantsInfo() {
		return plantsInfo;
	}
}
