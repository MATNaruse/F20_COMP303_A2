package comp303.a2.models;

public class CartItem {
	private String productName;
	private int quantity;
	private double productPrice;
	private double totalPrice;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	
	public CartItem(String prodName, double prodPrice, int quant) {
		this.productName = prodName;
		this.productPrice = prodPrice;
		this.quantity = quant;
	}
}
