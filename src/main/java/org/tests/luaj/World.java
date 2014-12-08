package org.tests.luaj;

import org.luaj.vm2.LuaValue;

import java.util.*;

public class World {
	public static World getInstance() {
		return instance;
	}

	static World instance = new World(30);

	public int getG() {
		return g;
	}

	int g;
	int size;
	List<List<List<Integer>>> map = new ArrayList<>();

	final Set<Integer> units = new HashSet<>();
	final Map<String, List<LuaValue>> listeners = new HashMap<>();

	public World(int size){
		this.size = size;
		for (int x=0; x<size; x++){
			List<List<Integer>> column = new ArrayList<>();
			for (int y=0; y<size; y++)
				column.add(new ArrayList<>());
			map.add(column);
		}
	}

	public List<Integer> getContentOf(int x, int y){
		return map.get(x).get(y);
	}

	public void move(Integer me, int x, int y){
		List<Integer> cell = getContentOf(x,y);
		if (!cell.contains(me))
			cell.add(me);
	}

	public Integer newUnit(){
		Integer unit = new Random(System.currentTimeMillis()).nextInt();
		synchronized (units){
			units.add(unit);
		}
		return unit;
	}

	public void addListener(String eventName, LuaValue function){
		synchronized (listeners){
			List<LuaValue> list = listeners.get(eventName);
			if (list == null){
				list = new ArrayList<>();
				listeners.put(eventName, list);
			}
			list.add(function);
		}
	}

	public void populateWorld(){
		for (int x=0; x<size; x++){
			List<List<Integer>> column = map.get(x);
			for (int y=0; y<size; y++) {
				List<Integer> objects = column.get(y);
				objects.add(new Random().nextInt(3));
			}
		}

		if (listeners.get("world_created")!= null){
			for (LuaValue listener: listeners.get("world_created"))
				listener.call();
		}
	}
}
