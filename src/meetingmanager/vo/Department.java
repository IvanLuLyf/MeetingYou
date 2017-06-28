package meetingmanager.vo;

public class Department {
	private String departmentid;
	private String name;

	public Department() {
		super();
	}

	public Department(String departmentid) {
		super();
		this.departmentid = departmentid;
	}

	public Department(String departmentid, String name) {
		super();
		this.departmentid = departmentid;
		this.name = name;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "{\"departmentid\":" + departmentid + ",\"name\":\"" + name + "\"}";
	}

}