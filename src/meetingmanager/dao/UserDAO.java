package meetingmanager.dao;

import java.sql.*;
import java.util.*;

import meetingmanager.util.*;
import meetingmanager.vo.*;

public class UserDAO {

	private Connection conn = null;

	public User selectByNamePwd(String username, String password) {
		User user = null;
		conn = DataBaseConnection.getConnection();
		try {
			PreparedStatement st = null;
			String sql = "select * from mm_user where username='" + username
					+ "' and  password='" + password + "'";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next() == true) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setDepartmentid(rs.getInt("departmentid"));
				user.setRole(rs.getInt("roleid"));
				user.setVerified(rs.getInt("verified"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return user;
	}

	public User selectByUsername(String username) {
		User user = null;
		conn = DataBaseConnection.getConnection();
		try {
			PreparedStatement st = null;
			String sql = "select * from mm_user where username='" + username
					+ "'";
			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next() == true) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setDepartmentid(rs.getInt("departmentid"));
				user.setRole(rs.getInt("roleid"));
				user.setVerified(rs.getInt("verified"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return user;
	}

	public void insert(User user) {
		conn = DataBaseConnection.getConnection();
		String sql = "insert into mm_user"
				+ "(username,password,email,phone,name,departmentid,verified,roleid)"
				+ " values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getName());
			pstmt.setInt(6, user.getDepartmentid());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 2);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
	}

	// 查询正在审核的员工信息，返回到集合中
	public List<User> selectUnverifiedUser() {
		conn = DataBaseConnection.getConnection();
		List<User> users = new ArrayList<User>();
		User user = null;
		try {
			PreparedStatement psd = null;
			String sql = "select * from mm_user where roleid=2 and verified=0";
			psd = conn.prepareStatement(sql);
			ResultSet rs = psd.executeQuery(sql);
			while (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setVerified(rs.getInt("verified"));
				user.setDepartmentid(rs.getInt("departmentid"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("roleid"));
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}

		return users;

	}

	// 向表employee中插入记录，其中status和role使用默认值
	public void updateStatus(int uid, int verified) {
		conn = DataBaseConnection.getConnection();
		String sql = "update mm_user set verified=" + verified + " where uid="
				+ uid;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
	}

	// 根据姓名、用户名、状态， 查询所有员工信息，返回到集合中。
	public List<User> selectUsersByNameStatus(String name, String username, int verified) {
		conn = DataBaseConnection.getConnection();

		List<User> users = new ArrayList<User>();
		User user = null;
		try {
			PreparedStatement st = null;
			String sql = null;
			String usernamesql, namesql, statussql;

			if (name == null || name.equals("")) {
				namesql = "";
			} else {
				namesql = " and name='" + name + "'";
			}

			if (username == null || username.equals("")) {
				usernamesql = "";
			} else {
				usernamesql = " and username='" + username + "'";
			}

			if (verified == 3) {
				statussql = "";
			} else {
				statussql = " and verified='" + verified + "'";
			}

			sql = "select * from mm_user where roleid=2 " + usernamesql + namesql + statussql;

			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setVerified(rs.getInt("verified"));
				user.setDepartmentid(rs.getInt("departmentid"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("roleid"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return users;
	}

	// 根据姓名、用户名、状态， 查询当前页的员工信息，返回到集合中。
	public List<User> selectUsersOfOnePage(String name, String username, int verified, int start,
			int count) {
		conn = DataBaseConnection.getConnection();

		List<User> users = new ArrayList<User>();
		User user = null;
		try {
			PreparedStatement st = null;
			String sql = null;
			String usernamesql, namesql, statussql;

			if (name == null || name.equals("")) {
				namesql = "";
			} else {
				namesql = " and name='" + name + "'";
			}

			if (username == null || username.equals("")) {
				usernamesql = "";
			} else {
				usernamesql = " and username='" + username + "'";
			}

			if (verified == -1 || verified == 3) {
				statussql = "";
			} else {
				statussql = " and verified='" + verified + "'";
			}

			// limit是MySQL中用来分页查询的，第一个int参数表示开始的索引，从0开始，第二个参数表示要查询的条数
			sql = "select * from mm_user where roleid=2 " + usernamesql + namesql + statussql + " limit "
					+ start + " ," + count;

			st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setVerified(rs.getInt("verified"));
				user.setDepartmentid(rs.getInt("departmentid"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("roleid"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return users;
	}
	
	public Map<Integer, User> selectAllMap(){
		conn = DataBaseConnection.getConnection();
		Map<Integer,User> users = new HashMap<Integer,User>();
		User user = null;
		try {
			PreparedStatement psd = null;
			String sql = "select * from mm_user";
			psd = conn.prepareStatement(sql);
			ResultSet rs = psd.executeQuery(sql);
			while (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setVerified(rs.getInt("verified"));
				user.setDepartmentid(rs.getInt("departmentid"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("roleid"));
				users.put(user.getUid(), user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return users;
	}
	
	public List<User> selectUserByDeptid(Integer deptid){
		conn = DataBaseConnection.getConnection();
		List<User> users = new ArrayList<User>();
		User user = null;
		try {
			PreparedStatement psd = null;
			String sql = "select * from mm_user where departmentid = " + deptid;
			psd = conn.prepareStatement(sql);
			ResultSet rs = psd.executeQuery(sql);
			while (rs.next()) {
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setVerified(rs.getInt("verified"));
				user.setDepartmentid(rs.getInt("departmentid"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("roleid"));
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}

		return users;
	}
	
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		List<User> users = dao.selectUsersByNameStatus("", "", 1);
		
		System.out.println(users);
	}

}
