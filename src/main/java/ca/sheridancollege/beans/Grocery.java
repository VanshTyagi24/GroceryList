package ca.sheridancollege.beans;

import java.io.Serializable;

public class Grocery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int grocId;
	private String grocName;
	private double price;
	private String category;
	public int getGrocId() {
		return grocId;
	}
	public void setGrocId(int grocId) {
		this.grocId = grocId;
	}
	public String getGrocName() {
		return grocName;
	}
	public void setGrocName(String grocName) {
		this.grocName = grocName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Grocery(int grocId, String grocName, double price, String category) {
		super();
		this.grocId = grocId;
		this.grocName = grocName;
		this.price = price;
		this.category = category;
	}
	public Grocery(String grocName, double price, String category) {
		super();
		this.grocName = grocName;
		this.price = price;
		this.category = category;
	}
	@Override
	public String toString() {
		return "Grocery [grocName=" + grocName + ", price=" + price + ", category=" + category
				+ "]";
	}
	public Grocery() {
		super();
	}
	
	
	
	
}
