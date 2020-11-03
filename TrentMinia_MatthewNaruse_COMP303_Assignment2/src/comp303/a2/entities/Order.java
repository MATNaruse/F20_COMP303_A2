/*
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
*/

package comp303.a2.entities;

import java.io.Serializable;
import java.lang.String;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity(name="Orders") @IdClass(OrderId.class)
public class Order implements Serializable {

	
	private int orderId;
	private int custId;
	private int productId;
	private int quantity;
	private String deliveryDate;
	private String creationDate;
	private String orderStatus;
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
	}   
	
	@Id    
	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}   
	@Id
	public int getCustId() {
		return this.custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	} 
	@Id
	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}   
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}   
	public String getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}   
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
   
//	public boolean isCancelable() {
//		// THIS SHOULD BE BETTER BUT I'M CURRENTLY BRAIN DEAD
//		// Get current date
//		Date Now = new Date();
//		
//		// DEBUG - Need Creation Date First
//		//if (this.creationDate == null) return false;
//		
//		this.creationDate = new GregorianCalendar(2020, Calendar.OCTOBER, 31).getTime();
//		int now_month = Now.getMonth();
//		int now_day = Now.getDate();
//		int cD_month = this.creationDate.getMonth();
//		int cD_day = this.creationDate.getDate();
//		Boolean monthCompare = now_month >= cD_month;
//		Boolean dayCompare = now_day >= cD_day && now_day <= cD_day + 1;
//		
//		System.out.println(String.format("[%b] Month Compare: now %d : cD %d", monthCompare, now_month, cD_month));
//		System.out.println(String.format("[%b] Day Compare: now %d : cD %d ", dayCompare, now_day, cD_day));
//		
//		return monthCompare && dayCompare;
//	}
	
}
