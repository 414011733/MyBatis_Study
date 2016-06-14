package domain;

import java.util.Date;

public class UserGroupLink {

	private User2 user;

	private Group group;

	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User2 getUser() {
		return user;
	}

	public void setUser(User2 user) {
		this.user = user;
	}
}