package DB.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.model.FoodOrder;
import DB.util.StringUtil;

/**
 * 订单的实体，负责增删查改
 * @author dell
 *
 */
public class FoodOrderDao {
	public int add(Connection con,FoodOrder foodOrder)throws Exception{
		String sql ="insert into foodorder values(null,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,foodOrder.getUserName());
		pstmt.setString(2, foodOrder.getFoodName());
		pstmt.setInt(3, foodOrder.getPrice());
		return pstmt.executeUpdate();		
	}
	public ResultSet list(Connection con, FoodOrder foodorder)throws Exception{
		StringBuffer sb=new StringBuffer("select * from foodorder");
		if(StringUtil.isNotEmpty(foodorder.getUserName())){
			sb.append(" and UserName like '%"+foodorder.getUserName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
}
