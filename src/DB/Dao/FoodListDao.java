package DB.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.model.FoodList;
import DB.util.StringUtil;
/**
 * �Բ˵�������ɾ�Ĳ�
 * @author dell
 *
 */

public class FoodListDao {
	/**
	 * �Բ˵����в�ѯ����
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
	 * �Բ˵������޸�
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
	 * �Բ˵����
	 */
	public int add(Connection con,FoodList foodlist)throws Exception{
		String sql ="insert into foodlist values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, foodlist.getFoodName());
		pstmt.setInt(2, foodlist.getPrice());
		return pstmt.executeUpdate();		
	}
	
}
