/*
	COMP303-001 Assignment 2
	Due Date: Nov 04, 2020
	Submitted: Nov 04, 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
*/

package comp303.a2.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 *
 */
@Entity

public class Product implements Serializable {

	   
	@Id
	private int productId;
	private String brandName;
	private String modelName;
	private double price;
	private String imgSrc;
	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}   
	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}   
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}   
	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}   
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}   
	public String getImgSrc() {
		if(this.imgSrc == null) return "css/phonePlaceholder.png";
		return this.imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
   
}
