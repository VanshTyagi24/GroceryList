package ca.sheridancollege.beans;

import java.io.Serializable;

public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String item;
	private double amount;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Cart(String item, double amount) {
		super();
		this.item = item;
		this.amount = amount;
	}
	public Cart() {
		super();
	}
	@Override
	public String toString() {
		return "Cart [item=" + item + ", amount=" + amount + "]";
	}
	
	
	
	
	
	
}
