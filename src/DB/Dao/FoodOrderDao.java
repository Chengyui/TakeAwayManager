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
		String sql ="insert into foodorder values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,foodOrder.getUserName());
		pstmt.setString(2, foodOrder.getFoodName());
		pstmt.setInt(3, foodOrder.getPrice());
		pstmt.setInt(4, 0);
		pstmt.setString(5, foodOrder.getEvaluation());
		return pstmt.executeUpdate();		
	}
	public int update(Connection con, String username, String foodname, String evaluation)throws Exception{
		StringBuffer sqlBuffer =new StringBuffer("update foodorder set evaluation='");
		sqlBuffer.append(evaluation);
		sqlBuffer.append("' where username='");
		sqlBuffer.append(username);
		sqlBuffer.append("' and foodname='");
		sqlBuffer.append(foodname);
		sqlBuffer.append("'");
		//System.out.println(sqlBuffer.toString());
		PreparedStatement pstmt=con.prepareStatement(sqlBuffer.toString());
		//pstmt.setString(1,foodOrder.getUserName());
		//pstmt.setString(2, foodOrder.getFoodName());
		//pstmt.setInt(3, foodOrder.getPrice());
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
	
	public int finish(Connection con,FoodOrder foodorder)throws Exception{
		String sql="update foodorder set done = 1 where username = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, foodorder.getUserName());
		return pstmt.executeUpdate();
		
	}
	public ResultSet list_1(Connection con, FoodOrder foodorder)throws Exception{
		StringBuffer sb=new StringBuffer("select username, sum(price) as price from foodorder group by username");
		if(StringUtil.isNotEmpty(foodorder.getUserName())){
			sb.append(" having UserName like '%"+foodorder.getUserName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	public ResultSet list_2(Connection con, FoodOrder foodorder)throws Exception{
		StringBuffer sb=new StringBuffer("select sum(price) as price from foodorder");
		if(StringUtil.isNotEmpty(foodorder.getUserName())){
			sb.append(" having UserName like '%"+foodorder.getUserName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	public ResultSet list_3(Connection con, FoodOrder foodorder, String username, String foodname)throws Exception{
		StringBuffer sb=new StringBuffer("select * from foodorder where username='");
		sb.append(username);
		sb.append("' and foodname='");
		sb.append(foodname);
		sb.append("'");
		//System.out.println(sb);
		if(StringUtil.isNotEmpty(foodorder.getUserName())){
			sb.append(" having UserName like '%"+foodorder.getUserName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
}
