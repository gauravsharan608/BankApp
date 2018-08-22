package com.mindtree.dao;

import java.util.Date;
import java.util.List;

import com.mindtree.entity.Account;
import com.mindtree.entity.Customer;
import com.mindtree.entity.Transaction;
import com.mindtree.exceptions.DaoException;

/**
 * @author M1030608
 * 
 *         This interface defines the headers of various methods implemented in
 *         DAO layer for the Bank application to interact with database.
 */
public interface BankDao {

	/**
	 * @param customer
	 * @return
	 * @throws DaoException
	 *             This method is meant to validate a user at the time he/she
	 *             logins. The credentials entered by the user are validated
	 *             against the data available in the database.
	 */
	boolean validateLogin(Customer customer) throws DaoException;

	String getFullName(String username) throws DaoException;

	List<String> getBenficiaries() throws DaoException;

	boolean creditAccount(long ac_no, double amount) throws DaoException;

	int insertTransaction(Transaction t) throws DaoException;

	boolean debitAccount(long ac_no, double amount) throws DaoException;

	List<Transaction> generateReport(Date date) throws DaoException;

	List<Account> fetchAccount(String username) throws DaoException;

	long getAccountNumber(String name) throws DaoException;

	boolean validateTransactionAmount(long debitAcc, double transferAmount)
			throws DaoException;

}
