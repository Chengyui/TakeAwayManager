package DB.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.model.FoodList;
import DB.util.StringUtil;
/**
 * 对菜单进行增删改查
 * @author dell
 *
 */

public class FoodListDao {
	/**
	 * 对菜单进行查询操作
	 *
	 */
	public ResultSet list(Connection con,FoodList foodList) throws Exception{
		StringBuffer sb=new StringBuffer("select * from foodlist");
		if(StringUtil.isNotEmpty(foodList.getFoodName())){
			sb.append(" and FoodName like '%"+foodList.getFoodName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	/**
	 * 对菜单进行修改
	 */
	public int update(Connection con,FoodList foodlist)throws Exception{
		String sql = "update foodlist set FoodName=?,Price=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, foodlist.getFoodName());
		pstmt.setInt(2, foodlist.getPrice());
		pstmt.setInt(3,foodlist.getId());
		return pstmt.executeUpdate();
	}
	/**
	 * 对菜单添加
	 */
	public int add(Connection con,FoodList foodlist)throws Exception{
		String sql ="insert into foodlist values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, foodlist.getFoodName());
		pstmt.setInt(2, foodlist.getPrice());
		return pstmt.executeUpdate();		
	}
	
}
