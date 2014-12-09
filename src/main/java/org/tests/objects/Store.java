package org.tests.objects;

import java.util.Random;

public class Store {
	public Fruit fruit;

	public Integer id = new Random(System.currentTimeMillis()).nextInt(10000);

	public Store(Fruit fruit){
		this.fruit = fruit;
	}

	public Fruit getSellingFruit(){return fruit;}
}
