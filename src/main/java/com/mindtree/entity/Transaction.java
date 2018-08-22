/**
 * 
 */
package com.mindtree.entity;

import java.util.Date;

/**
 * @author M1030608
 * 
 *         This entity class defines a transaction. It contains various
 *         attributes specific to a transaction.
 *
 */
public class Transaction {

	/**
	 * id : Uniquely identifies a transaction
	 */
	private int id;
	/**
	 * debitAccount : Defines the account from which sum is debited.
	 */
	private Account debitAccount;

	/**
	 * creditAccount : Defines the account to which sum is credited.
	 */
	private Account creditAccount;
	/**
	 * transactionAmount : Defines amount of sum transferred.
	 */
	private double transactionAmount;
	/**
	 * The data on which transaction is done
	 */
	private Date transactionDate;
	/**
	 * Description about the transaction.
	 */
	private String detail;

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * Non-arg constructor to facilitate creating an object having default
	 * values of the properties
	 */
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param id
	 * @param debitAccount
	 * @param creditAccount
	 * @param transactionAmount
	 * @param transactionDate
	 * @param detail
	 */
	public Transaction(int id, Account debitAccount, Account creditAccount,
			double transactionAmount, Date transactionDate, String detail) {
		this.id = id;
		this.debitAccount = debitAccount;
		this.creditAccount = creditAccount;
		this.transactionAmount = transactionAmount;
		this.transactionDate = transactionDate;
		this.detail = detail;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the debitAccount
	 */
	public Account getDebitAccount() {
		return debitAccount;
	}

	/**
	 * @param debitAccount
	 *            the debitAccount to set
	 */
	public void setDebitAccount(Account debitAccount) {
		this.debitAccount = debitAccount;
	}

	/**
	 * @return the creditAccount
	 */
	public Account getCreditAccount() {
		return creditAccount;
	}

	/**
	 * @param creditAccount
	 *            the creditAccount to set
	 */
	public void setCreditAccount(Account creditAccount) {
		this.creditAccount = creditAccount;
	}

	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}

	/**
	 * @param transactionAmount
	 *            the transactionAmount to set
	 */
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creditAccount == null) ? 0 : creditAccount.hashCode());
		result = prime * result
				+ ((debitAccount == null) ? 0 : debitAccount.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(transactionAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Transaction other = (Transaction) obj;
		if (creditAccount == null) {
			if (other.creditAccount != null)
				return false;
		} else if (!creditAccount.equals(other.creditAccount))
			return false;
		if (debitAccount == null) {
			if (other.debitAccount != null)
				return false;
		} else if (!debitAccount.equals(other.debitAccount))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(transactionAmount) != Double
				.doubleToLongBits(other.transactionAmount))
			return false;
		return true;
	}

}
