package meetingmanager.service;

import java.util.List;

import meetingmanager.dao.*;
import meetingmanager.vo.*;

public class UserService {

	private UserDAO dao = new UserDAO();
	private User loginedUser = null;
	private int countOfPages;
	private int countOfUsers;
	private int pageSize = 3;
	
	public int login(String username, String password) {
		int verified = 3;
		User user = dao.selectByNamePwd(username, password);
		if (user != null) {
			loginedUser = user;
			verified = user.getVerified();
		}
		return verified;
	}

	public User getLoginedUser() {
		return loginedUser;
	}

	public int regist(User employee) {
		int flag = 0;
		User user = dao.selectByUsername(employee.getUsername());
		if (user == null) {
			flag = 1;
			dao.insert(employee);
		}
		return flag;
	}

	public List<User> searchUsers(String name, String username, int verified) {
		List<User> list = dao.selectUsersByNameStatus(name, username, verified);
		countOfUsers = list.size();
		return list;
	}

	public List<User> searchUsersOfOnePage(String name, String username, int verified, int start,
			int count) {
		return dao.selectUsersOfOnePage(name, username, verified, start, count);
	}

	// 返回总页数
	public int getCountOfPages() {
		countOfPages = (countOfUsers % pageSize == 0) ? countOfUsers / pageSize
				: countOfUsers / pageSize + 1;
		return this.countOfPages;
	}

	// 返回所有记录条数
	public int getCountOfUsers() {
		return this.countOfUsers;
	}

	// 返回每页的记录条数，默认为3
	public int getPageSize() {
		return this.pageSize;
	}
	
	public static void main(String[] args) {
		UserService service = new UserService();
		System.out.println(service.regist(new User("黄美玲","huangml002","1",1,"13567898765","huangml@qq.com",0,2)));
		User user = service.getLoginedUser();
		System.out.println(user);
	}

}
