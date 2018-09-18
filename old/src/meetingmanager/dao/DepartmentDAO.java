package meetingmanager.dao;

import java.sql.*;
import java.util.*;

import meetingmanager.util.DataBaseConnection;
import meetingmanager.vo.Department;

public class DepartmentDAO {

	private Connection conn;

	public List<Department> selectAll() {
		conn = DataBaseConnection.getConnection();
		List<Department> departments = new ArrayList<Department>();
		try {
			Statement st = null;
			String sql = "select * from mm_department";
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Department department;
			while (rs.next()) {
				department = new Department();
				department.setDepartmentid(rs.getString("departmentid"));
				department.setName(rs.getString("name"));
				departments.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
		return departments;
	}

	// 向表department中插入记录
	public void insert(String departmentname) {
		conn = DataBaseConnection.getConnection();
		String sql = "insert into mm_department (name) values(?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, departmentname);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
	}

	// 根据id删除一个部门
	public void delete(int departmentid) {
		conn = DataBaseConnection.getConnection();
		String sql = "delete from mm_department where departmentid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, departmentid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnection.closeConnection();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DepartmentDAO dao = new DepartmentDAO();
		List<Department> list = dao.selectAll();
		for (Department d : list) {
			System.out.println(d);
		}
	}

}
