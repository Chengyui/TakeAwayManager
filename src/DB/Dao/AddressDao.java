package DB.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.model.Address;
import DB.model.FoodList;
/**
 * 地址的增删查改
 * @author dell
 *
 */
public class AddressDao {
	public int add(Connection con,Address address)throws Exception{
		String sql ="insert into address values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, address.getUserName());
		pstmt.setString(2, address.getAddress());
		return pstmt.executeUpdate();		
	}
	public ResultSet list(Connection con,Address address)throws Exception{
		String sql = "select * from address where username = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, address.getUserName());
		return pstmt.executeQuery();
	}
}
