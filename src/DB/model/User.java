package DB.model;

/**
 * 存放用户的名称，密码，用户类别
 * @author 程锦国
 *
 */

public class User {
	private int id; //用户编号
	private String userName; //用户的账户名
	private String passWord; //用户的密码
	private int Type;	// 用户类别
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	
	public User() {
		super();
	}
	public User(String userName,String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
}
