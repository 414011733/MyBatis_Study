package domain;

import java.util.List;

public class Group {

	private long id;

	private String name; // 组名

	private int state; // 0可见状态 1不可见状态

	private List<User2> user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<User2> getUser() {
		return user;
	}

	public void setUser(List<User2> user) {
		this.user = user;
	}

}
