package test;

import java.io.Reader;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Group;
import domain.User2;
import domain.UserGroupLink;

/**
 * @describe: 测试用例
 * @author: Nirvana
 * @version: V1.0 2011-3-4下午03:21:17 create
 */
public class TestManyToMany {

	private final static String IBATIS_CONFIG_XML = "conf.xml";
	private static SqlSession session;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Reader reader = Resources.getResourceAsReader(IBATIS_CONFIG_XML); // 读取ibatis配置文件
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		session = sqlMapper.openSession(true);
		session.commit(true); // 将默认提交事务属性设置为否
	}

	// 保存用户信息
	@Test
	public void saveUserTest() {
		User2 user = new User2();
		user.setName("张三");
		user.setAge(12);
		session.insert("mapping.user2Mapper.saveUser", user);
	}

	// 获取用户信息
	@Test
	public void getUserTest() {
		User2 user = (User2) session.selectOne("mapping.user2Mapper.selectUser", 47L);
		System.out.println("用户名： " + user.getName());
		System.out.println("用户年龄：  " + user.getAge());
	}

	// 获取用户和用户所在组信息
	@Test
	public void getUserAndGroupTest() {
		User2 user = (User2) session.selectOne("mapping.user2Mapper.selectUserGroup", 1L);

		System.out.println(user.getName() + "所属组信息:");
		for (Group group : user.getGroup()) {
			System.out.println("	组名:" + group.getName());
		}

	}

	// 保存用户和用户所在组信息
	// 当用户所在组不存在时，创建该组，并生成映射关系
	@Test
	public void saveUserAndGroupTest() {
		User2 user = new User2();
		user.setName("无常鬼");
		user.setAge(13);
		session.insert("mapping.user2Mapper.saveUser", user);

		Group groupImpl = (Group) session.selectOne("mapping.groupMapper.getGroupByName", "用户组4");// 获取组实例
		UserGroupLink ugl = new UserGroupLink();// 声明User和Group实体间映射关系实例

		// 查询到的组实例为空时的逻辑处理
		if (groupImpl == null) {
			Group group = new Group();
			group.setName("用户组4");
			session.insert("mapping.groupMapper.saveGroup", group);// 持久化创建好的组实例

			// 设置映射关系实例相关的属性
			ugl.setUser(user);
			ugl.setGroup(group);
			session.insert("mapping.user2Mapper.saveRelativity", ugl);// 持久化映射关系实力

		} else {
			ugl.setGroup(groupImpl);
			ugl.setUser(user);
			session.insert("mapping.user2Mapper.saveRelativity", ugl);
		}
	}

	// 删除组信息的同时，取消组内所有的成员与该组的关联关系
	@Test
	public void deleteGroupTest() {
		Group group = new Group();
		// group.setId(1L); //以组id作为查询条件
		group.setName("用户组4"); // 以组name作为查询条件
		Group groupImpl = (Group) session.selectOne("mapping.groupMapper.selectGroupUser", group);// 获取组实例

		// 组实例存在时
		if (groupImpl != null) {

			List<User2> users = groupImpl.getUser();

			// 查看用户组1中是否存在用户
			if (users != null && users.size() > 0) {

				// 存在用户时，先删除组与用户的对应关系
				UserGroupLink ugl = new UserGroupLink();
				for (User2 user : users) {
					ugl.setUser(user);
					ugl.setGroup(groupImpl);
					session.delete("mapping.groupMapper.deleteGroupUser", ugl);
				}
			}
			// 删除组信息
			session.delete("mapping.groupMapper.deleteGroup", groupImpl);

		} else {
			throw new RuntimeException("查询的组不存在!!");
		}

	}

	// 更新组状态，当组状态由可见状态变成不可见时，取消该组下的用户与该组的映射关系
	@Test
	public void updateGroupStateTest() {
		Group group = new Group();
		group.setName("用户组4");
		Group groupImpl = (Group) session.selectOne("mapping.groupMapper.selectGroupUser", group);

		if (groupImpl != null) {
			int state = groupImpl.getState() == 1 ? 0 : 1;

			// 组状态由0变成1时，即由可见变为不可见
			if (state == 1) {
				List<User2> users = groupImpl.getUser();
				// 查看用户组2中是否存在用户
				if (users != null && users.size() > 0) {

					// 存在用户时，删除组与用户的对应关系
					UserGroupLink ugl = new UserGroupLink();
					for (User2 user : users) {
						ugl.setUser(user);
						ugl.setGroup(groupImpl);
						session.delete("mapping.groupMapper.deleteGroupUser", ugl);
					}
				}
			}

			// 更新组状态
			groupImpl.setState(state);
		} else {
			throw new RuntimeException("查询的组不存在!!");
		}

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (session != null) {
//			session.commit(); // 提交事务
			session.close(); // 关闭连接
		}
	}
}
