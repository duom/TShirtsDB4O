package Entities;
/**
 * @author Joan Anton Pérez Braña
 * @since 19/02/2017
 *
 */
public class CreditCard {
	private String number;
	private String securityNumber;
	private int lastMonth;
	private int lastYear;
	
	public CreditCard(String number, String securityNumber, int lastMonth, int lastYear) {
		super();
		this.number = number;
		this.securityNumber = securityNumber;
		this.lastMonth = lastMonth;
		this.lastYear = lastYear;
	}
	
	public CreditCard() {
		super();
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSecurityNumber() {
		return securityNumber;
	}
	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
	}
	public int getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(int lastMonth) {
		this.lastMonth = lastMonth;
	}
	public int getLastYear() {
		return lastYear;
	}
	public void setLastYear(int lastYear) {
		this.lastYear = lastYear;
	}
	
	@Override
	public String toString() {
		return "CreditCard [number=" + number + ", securityNumber=" + securityNumber + ", lastMonth=" + lastMonth
				+ ", lastYear=" + lastYear + "]";
	}

}