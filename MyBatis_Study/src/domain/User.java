package domain;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String name;
	private int age;

	public User() {
	}
	
	public User(int i, String string, int j) {
		this.id = i;
		this.name = string;
		this.age = j;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
