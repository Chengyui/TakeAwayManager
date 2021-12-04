package DB.model;

/**
 * 每日收入 包括日期和日收入
 * @author Lenovo
 *
 */

public class incomePerDay {
	private int id;
	private String date;
	private int income;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
}
