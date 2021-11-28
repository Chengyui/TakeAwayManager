package DB.Dao;

import java.sql.Connection;
import java.sql.*;
import DB.model.User;

public class UserDao {
	/**
	 * 登录验证，判断用户名和密码是否正确
	 */
	public User login(Connection con,User user)throws Exception{
		User resultUser=null;
		String sql="select * from User where username=? and password=?";//查找数据库的接口
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassWord());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassWord(rs.getString("passWord"));
			resultUser.setType(rs.getInt("Type"));
		}
		return resultUser;
	}
}