package Entities;

import java.util.Date;
import java.util.Set;
/**
 * @author Joan Anton Pérez Braña
 * @since 19/02/2017
 *
 */
public class Order {
	private int idOrder;
	private Date orderDate;
	private Date deliveryDate;
	private Customer customer;
	private Set<OrderDetail> details;
	
	public Order(int idOrder, Date orderDate, Date deliveryDate, Customer customer, Set<OrderDetail> details) {
		super();
		this.idOrder = idOrder;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.customer = customer;
		this.details = details;
	}

	public Order() {
		super();
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<OrderDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", orderDate=" + orderDate + ", deliveryDate=" + deliveryDate
				+ ", customer=" + customer + "]";
	}

}