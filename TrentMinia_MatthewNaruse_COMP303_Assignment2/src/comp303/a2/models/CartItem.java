/*
	COMP303-001 Assignment 2
	Due Date: Nov 04, 2020
	Submitted: Nov 04, 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
*/

package comp303.a2.models;

import comp303.a2.entities.Product;

public class CartItem {
	private String productName;
	private int productId;
	private int quantity;
	private double productPrice;
	private double totalPrice;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public double getTotalPrice() {
		return this.quantity * this.productPrice;
	}
	
	public void AddQuantity(int addQ) {this.quantity += addQ;}
	public void RemQuantity(int remQ) {this.quantity -= remQ;}
	
	public CartItem() {}
	
	public CartItem(String prodName, int prodId, double prodPrice, int quant) {
		this.productName = prodName;
		this.productId = prodId;
		this.productPrice = prodPrice;
		this.quantity = quant;
	}
	
	public CartItem(Product prod, int quant) {
		this.productName = prod.getModelName();
		this.productId = prod.getProductId();
		this.productPrice = prod.getPrice();
		this.quantity = quant;
	}
}
