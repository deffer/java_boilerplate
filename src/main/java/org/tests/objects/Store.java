package org.tests.objects;

import java.util.Random;

public class Store {
	public static enum Category {
		SUPER,FOOD,ELECTRICAL
	}

	public static final Random RND = new Random(System.currentTimeMillis());
	public Fruit fruit;
	public Category cat;

	public Integer id = RND.nextInt(10000);

	public Store(Fruit fruit){
		this.fruit = fruit;
		this.cat = Category.FOOD;
	}

	public Fruit getSellingFruit(){return fruit;}
}
