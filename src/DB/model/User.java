package DB.model;

/**
 * ����û������ƣ����룬�û����
 * @author �̽���
 *
 */

public class User {
	private int id; //�û����
	private String userName; //�û����˻���
	private String passWord; //�û�������
	private int Type;	// �û����
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
