package meetingmanager.service;

import java.util.*;

import meetingmanager.dao.*;
import meetingmanager.vo.*;

public class DepartmentService {
	private DepartmentDAO dao=new DepartmentDAO();

	public List<Department> viewAllDepartments(){
		return dao.selectAll();
	}
	
	public void addDepartment(String departmentname){
		dao.insert(departmentname);
	}
	
	public void deleteDepartment(int departmentid){
		dao.delete(departmentid);
	}
}
