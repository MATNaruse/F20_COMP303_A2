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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
   
	public String getPlainCreationDate() {
		return (String) this.creationDate.subSequence(0, 10);
	}

	public boolean isCancelable() {
		// Compares if the current time is within the window of 24hr since order creation
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date Now = new Date();
			Date windowStart = sdf.parse(this.creationDate);
			Date windowEnd = new Date(windowStart.getTime() + 86400000); // Add 24hrs in Milliseconds
			System.out.println("Order:isCancelable: CHECKING CANCELABLE");
			System.out.println("Order:isCancelable: windowStart:" + windowStart.toString());
			System.out.println("Order:isCancelable: --------Now:" + Now.toString());			
			System.out.println("Order:isCancelable: --windowEnd:" + windowEnd.toString());
			if (Now.compareTo(windowStart) > 0 && Now.compareTo(windowEnd) < 0) {
				System.out.println("Order:isCancelable: YES IT'S CANCELABLE");	
				return true;
			}
			else {
				System.out.println("Order:isCancelable: NOPE IT'S NOT CANCELABLE");	
				return false;
			}
		}		
		catch(ParseException pe) {
			System.out.println("Order:isCancelable: " + pe.getMessage());
			return false;
		}
		
	}
	
}
