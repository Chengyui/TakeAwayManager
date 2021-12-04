package DB.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import DB.model.FoodOrder;
import DB.model.incomePerDay;

public class incomePerDayDao {
	public int add(Connection con,incomePerDay incomeperday)throws Exception{
		String sql ="insert into incomeperday values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,incomeperday.getDate());
		pstmt.setInt(2, incomeperday.getIncome());
		return pstmt.executeUpdate();		
	}
}