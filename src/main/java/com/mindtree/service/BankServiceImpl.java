/**
 * 
 */
package com.mindtree.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mindtree.dao.BankDaoImpl;
import com.mindtree.entity.Account;
import com.mindtree.entity.Customer;
import com.mindtree.entity.Transaction;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.InvalidInputException;
import com.mindtree.exceptions.PersistException;
import com.mindtree.exceptions.ServiceExcption;

/**
 * @author M1030608
 * 
 *         This class implements the various methods meant to define business
 *         logics declared in the ServiceDao interface.
 *
 */
public class BankServiceImpl implements BankService {

	/**
	 * @param customer
	 * @return
	 * @throws FetchException
	 *             This method takes the credentials of customer from Controller
	 *             and passes it to the DAO layer for validation.
	 */
	public boolean validateLogin(Customer customer) throws FetchException {
		try {
			return new BankDaoImpl().validateLogin(customer);
		} catch (DaoException e) {
			throw new FetchException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param t
	 * @return
	 * @throws PersistException
	 *             This method is part of transaction. It performs debit, credit
	 *             and updating of the trnasction table as pert of the
	 *             transaction.
	 * @throws InvalidInputException
	 */
	public boolean transferFund(Transaction t) throws PersistException, InvalidInputException {
		long debitAcc = t.getDebitAccount().getAccountNumber();
		double transferAmount = t.getTransactionAmount();
		boolean validAmount = false;
		try {
			validAmount = new BankDaoImpl().validateTransactionAmount(debitAcc, transferAmount);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
		if (!validAmount || transferAmount <= 0)
			throw new InvalidInputException("Amount  of transaction is invalid");
		else {

			boolean success = false;
			int tr = 0;

			try {
				tr = new BankDaoImpl().insertTransaction(t);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (tr > 0) {
				boolean debited = false;
				try {
					debited = new BankDaoImpl().debitAccount(t.getDebitAccount().getAccountNumber(),
							t.getTransactionAmount());
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					throw new PersistException(e.getMessage(), e);
				}
				if (debited == true) {
					try {
						success = new BankDaoImpl().creditAccount(t.getCreditAccount().getAccountNumber(),
								t.getTransactionAmount());
					} catch (DaoException e) {

						throw new PersistException(e.getMessage(), e);
					}
				}

			}

			return success;
		}
	}

	/**
	 * 
	 * @param username
	 * @return
	 * @throws FetchException
	 *             This method fetches the list of accounts related to a
	 *             specific customer.
	 * @throws InvalidInputException
	 */
	public List<Account> fetchAccount(String username) throws FetchException, InvalidInputException {
		try {
			List<Account> list = new BankDaoImpl().fetchAccount(username);
			if (list.size() == 0) {
				throw new InvalidInputException("Invalid User");
			}
			return list;
		} catch (DaoException e) {

			throw new FetchException(e.getMessage());
		}
	}

	public List<String> getBenficiaries() throws FetchException {
		try {
			return new BankDaoImpl().getBenficiaries();
		} catch (DaoException e) {

			throw new FetchException(e.getMessage());
		}
	}

	public long getAccountNumber(String name) throws FetchException {
		try {
			return new BankDaoImpl().getAccountNumber(name);
		} catch (DaoException e) {
			throw new FetchException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws FetchException
	 * @throws IOException
	 *             This method fetches the transactions corresponding to a
	 *             specific date to generate report.
	 * @throws InvalidInputException
	 * @throws ServiceExcption
	 * 
	 */
	public List<Transaction> generateReport(Date date) throws ServiceExcption, FetchException {

		List<Transaction> list = new ArrayList<Transaction>();
		Date currDate = new java.util.Date();
		if (date == null || date.after(currDate)) {
			try {
				throw new InvalidInputException("Date is invalid");
			} catch (InvalidInputException e) {
				throw new ServiceExcption(e);
			}
		}
		try {
			list = new BankDaoImpl().generateReport(date);
			for (Transaction t : list) {
				writeTransactionToFile(t);
			}
		} catch (DaoException e) {
			throw new FetchException(e.getMessage());
		}
		return list;
	}

	/**
	 * 
	 * @param t
	 * @throws ServiceExcption
	 * @throws IOException
	 *             This method is meant to save the generated report to a file.
	 */
	private void writeTransactionToFile(Transaction t) throws ServiceExcption {

		String fileName = "Report.txt";
		String newLine = System.getProperty("line.separator");

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File("D:" + File.separator + fileName), true));

			bw.write("Transaction Details" + newLine);
			bw.write("**************************************\n" + newLine);
			bw.write(
					"  A/C No         |       Beneficiary A/C No        |       Transaction Amount         " + newLine);
			bw.write("+--------------------------------------------------------------+" + newLine);

			bw.write("|" + t.getDebitAccount().getAccountNumber() + "\t\t\t\t    |    \t\t\t  "
					+ t.getCreditAccount().getAccountNumber() + "\t\t\t\t\t    |     " + t.getTransactionAmount()
					+ newLine);

			bw.write(newLine);
			bw.write("-------------End---------------------" + newLine);

		} catch (IOException e) {
			throw new ServiceExcption(e);
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				throw new ServiceExcption(e);
			}
		}
	}

}
