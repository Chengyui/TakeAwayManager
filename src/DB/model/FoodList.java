package DB.model;
/**
 * 存放菜单实体，菜品名称，菜品价格
 * @author dell
 *
 */
public class FoodList {
	private int id;
	private int Price;
	private String foodName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String toString() {
		return foodName;
	}
	
}
