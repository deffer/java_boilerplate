package org.tests.objects;

public class Store {
	public Fruit fruit;
	public Fruit getSellingFruit(){return fruit;}

	public Store(Fruit fruit){
		this.fruit = fruit;
	}
}
