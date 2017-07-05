package meetingmanager.vo;

public class User {

	private int uid;// 用户id
	private String username;// 用户名
	private String password;// 用户密码
	private String email;// 用户邮箱
	private String phone;// 联系电话
	private String name;// 姓名
	private int departmentid;// 部门
	private int verified; // 审核状态
	private int roleid; // 角色
	private String token;
	
	public User() {
		this.uid = 0;
		this.username = "";
		this.password = "";
		this.email = "";
		this.phone = "";
		this.name = "";
		this.departmentid = 0;
		this.verified = 0;
		this.roleid = 1;
		token = "";
	}

	public User(String name, String username, String password,
			int departmentid, String email, String phone, int verified,
			int roleid) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.departmentid = departmentid;
		this.email = email;
		this.phone = phone;
		this.verified = verified;
		this.roleid = roleid;
		token = "";
	}

	public static void main(String[] args) {
		User u = new User();
		u.setUid(1);
		u.setUsername("Ivan");
		u.setPassword("123");
		u.setEmail("835498692@qq.com");
		u.setPhone("15650750369");
		u.setName("卢逸芳");
		u.setDepartmentid(1);
		u.setRole(1);
		u.setVerified(1);
		u.setDepartmentid(1);
		System.out.println(u);
	}

	@Override
	public String toString() {
		return "{\"uid\":\"" + uid + "\",\"username\":\"" + username
				+ "\",\"password\":\"" + password + "\",\"email\":\"" + email
				+ "\",\"phone\":\"" + phone + "\",\"name\":\"" + name
				+ "\",\"departmentid\":" + departmentid + ",\"verified\":"
				+ verified + ",\"roleid\":" + roleid + "}";
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	public int getRoleId() {
		return roleid;
	}

	public void setRole(int roleid) {
		this.roleid = roleid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}