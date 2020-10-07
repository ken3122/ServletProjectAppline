package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("serial")
public class Model implements Serializable {
	
	private static final Model instance = new Model();
	private final Map<Integer, User> model;
	
	public static Model getInstance() {
		return instance;
	}
	
	private Model() {
		model = new HashMap<>();
		
		model.put(1, new User("Sergey", "Morozov", 50000));
		model.put(2, new User("Ivan", "Ivanov", 60000));
		model.put(3, new User("John", "Johnson",  100000));
	}
	
	public void add(User user, int id) {
		model.put(id, user);
	}
	
	public Map<Integer, User> getFromList(){
		return model;
	}
	
	public void remove(int id) {
		model.remove(id);
	}
	
	public boolean containsKey(int id) {
		return model.containsKey(id);
	}
	
//	public void update(int id, User user) {
//		
//	}
}
