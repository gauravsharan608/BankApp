/**
 * 
 */
package com.mindtree.entity;

/**
 * @author M1030608
 * 
 *         This entity class defines an account. It contains properties
 *         applicable for an account and also defines various getters and
 *         setters methods to define behavior of the corresponding object.
 *
 */
public class Account {

	private long accountNumber;
	private double balance;
	private Customer customer;

	/**
	 * This is a no-arg constructor.It facilitates creating a new object with
	 * its attributes having default values.
	 */
	public Account() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param accountNumber
	 * @param balance
	 * @param customer
	 *            This constructor facilitates creating new objects with
	 *            properties having some initial values.
	 * 
	 */
	public Account(long account_number, double balance, Customer customer) {
		this.accountNumber = account_number;
		this.balance = balance;
		this.customer = customer;
	}

	/**
	 * @return the accountNumber
	 */
	public long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(long account_number) {
		this.accountNumber = account_number;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (accountNumber ^ (accountNumber >>> 32));
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

}
