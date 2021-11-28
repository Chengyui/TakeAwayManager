package DB.model;
/**
 * 订单表实体，订单号，食物号，顾客编号
 * @author dell
 *
 */
public class FoodOrder {
	private int id;
	private String userName;
	private String FoodName;
	private int Price;
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
	public String getFoodName() {
		return FoodName;
	}
	public void setFoodName(String foodName) {
		FoodName = foodName;
	}
	public int getPrice() {
		return Price;
	}
	
	public void setPrice(int price) {
		Price = price;
	}
	public String toString() {
		return userName;
	}
}
