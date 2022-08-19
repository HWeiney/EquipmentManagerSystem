package pres.hw.ems.domain;

public class User {

	private int userid;
	private String userName;
	private String password;
	private String phone;
	private String type;
	private String createtime;
	private String updatetime;

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, String type) {
		this.userName = username;
		this.password = password;
		this.type = type;
	}

	public User(int userid, String username, String phone, String type) {
		this.userid = userid;
		this.userName = username;
		this.phone = phone;
		this.type = type;
		
	}
	
	public User(int userid, String userName, String password, String phone, String type, String createtime,
			String updatetime) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.type = type;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

}
