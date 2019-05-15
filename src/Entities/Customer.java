package Entities;
/**
 * @author Joan Anton Pérez Braña
 * @since 19/02/2017
 *
 */
public class Customer {
	private int idCustomer;
	private String name;
	private String address;
	private String email;
	private String phone;
	private CreditCard creditCard;
	
	public Customer(int idCustomer, String name, String address, String email, String phone, CreditCard creditCard) {
		super();
		this.idCustomer = idCustomer;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.creditCard = creditCard;
	}
	
	public Customer() {
		super();
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
}