package domain;

import java.io.Serializable;
import java.util.List;

public class User2 implements Serializable{
	private int id;
	private String name;
	private int age;
	private List<Group> group;

	public User2() {
	}
	
	public User2(int i, String string, int j) {
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
	
	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
