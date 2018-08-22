/**
 * 
 */
package com.mindtree.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mindtree.entity.Account;
import com.mindtree.entity.Customer;

import com.mindtree.entity.Transaction;

import com.mindtree.exceptions.DBUtilityException;
import com.mindtree.exceptions.DaoException;

/**
 * @author M1030608
 *
 */
public class BankDaoImpl implements BankDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mindtree.dao.jdbc.BankDao#validateLogin(com.mindtree.entity.Customer)
	 */
	public boolean validateLogin(Customer customer) throws DaoException {
		String sql = "select count(*) from customers  where username= '" + customer.getUsername() + "' and password = '"
				+ customer.getPassword() + "'";

		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			int count = 0;
			if (rs.next()) {

				count = rs.getInt(1);
			}

			if (count > 0)
				return true;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				throw new DaoException(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * This method generates full name by accessing first name and last name
	 * from te database.
	 */
	public String getFullName(String username) throws DaoException {
		String sql = "select first_name,last_name from customers where username= '" + username + "'";
		String fullName = null;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				fullName = rs.getString(1) + " " + rs.getString(2);

			}
			return fullName;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws DaoException
	 * 
	 *             This method is meant to fetch account number corresponding to
	 *             the beneficiary name that it receives.
	 */
	public long getAccountNumber(String name) throws DaoException {
		String sql = "select beneficiary_ac_no from beneficiaries where beneficiary_name= '" + name + "'";
		long ac_no = 0L;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				ac_no = rs.getLong(1);

			}
			return ac_no;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @return
	 * @throws DaoException
	 *             This method fetches all the beneficiaries from the database.
	 */
	public List<String> getBenficiaries() throws DaoException {
		String sql = "select beneficiary_name from beneficiaries";

		List<String> beneficiaries = new ArrayList<String>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			// TODO Auto-generated catch block
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				beneficiaries.add(rs.getString(1));

			}
			return beneficiaries;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param username
	 * @return
	 * @throws DaoException
	 *             This method receives username as its argument abd fetches
	 *             list of all the acconts corresponding to that user.
	 */
	public List<Account> fetchAccount(String username) throws DaoException {

		String sql = "select account_no, balance from accounts where ";
		sql += " customer_id in (select id from customers where username= ? )";
		List<Account> al = new ArrayList<Account>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			throw new DaoException(e1.getMessage(), e1);
		}
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				Account account = new Account();
				account.setAccountNumber(rs.getLong(1));
				account.setBalance(rs.getDouble(2));
				al.add(account);
			}

			return al;
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				throw new DaoException(e.getMessage());
			}
		}

	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws DaoException
	 *             This method takes date as its argumenta and fetches the list
	 *             of transactions performed on that day.
	 */
	public List<Transaction> generateReport(Date date) throws DaoException {
		String sql = "select account_no, beneficiary_ac_no, transaction_amount ";
		sql += " from transactions where transaction_date =?";
		List<Transaction> al = new ArrayList<Transaction>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			throw new DaoException(e1.getMessage(), e1);
		}
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setDate(1, new java.sql.Date(date.getTime()));
			ResultSet rs = st.executeQuery();
			while (rs.next()) {

				Transaction transaction = new Transaction();
				Account creditAccount = new Account(rs.getLong(1), 0.0, null);
				Account debitAccount = new Account(rs.getLong(2), 0.0, null);
				transaction.setCreditAccount(creditAccount);
				transaction.setDebitAccount(debitAccount);
				transaction.setTransactionDate(date);
				transaction.setTransactionAmount(rs.getDouble(3));
				al.add(transaction);
			}

			return al;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				// TODO Auto-generated catch block
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param ac_no
	 * @param amount
	 * @return
	 * @throws DaoException
	 * 
	 *             This method is part of transaction.It debits the transaction
	 *             amount from the debit account.
	 * 
	 */
	public boolean debitAccount(long ac_no, double amount) throws DaoException {
		String sql = "update accounts set balance = balance-" + amount + " where account_no= " + ac_no;

		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			int updated = st.executeUpdate(sql);
			if (updated > 0) {

				return true;
			} else
				return false;
		} catch (SQLException e) {
			throw new DaoException("Unable to debit");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param t
	 * @return
	 * @throws DaoException
	 *             This methhod is part of transcation and its role is to update
	 *             the transactio table with the attributes of the trnasaction.
	 */
	public int insertTransaction(Transaction t) throws DaoException {
		String sql = " insert into transactions values(null,?,?,?,?,?)";

		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			throw new DaoException(e1.getMessage(), e1);
		}
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setLong(1, t.getDebitAccount().getAccountNumber());
			st.setLong(2, t.getCreditAccount().getAccountNumber());
			st.setDouble(3, t.getTransactionAmount());
			st.setDate(4, new java.sql.Date(t.getTransactionDate().getTime()));
			st.setString(5, t.getDetail());
			int updated = st.executeUpdate();
			return updated;
		} catch (SQLException e) {
			throw new DaoException("Unable to credit");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param ac_no
	 * @param amount
	 * @return
	 * @throws DaoException
	 *             This method is part of the transaction and it updates the
	 *             balance of beneficiary by adding the transaction amount to
	 *             the current sum.
	 */
	public boolean creditAccount(long ac_no, double amount) throws DaoException {
		String sql = "update accounts set balance = balance+" + amount + " where account_no= " + ac_no;

		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();
			int updated = st.executeUpdate(sql);
			if (updated > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			throw new DaoException("Unable to credit");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				throw new DaoException(e.getMessage());
			}
		}
	}

	/**
	 * This method validates the amount being entered for transfer. if the
	 * amount is greater than available balnce, it returns false.
	 */
	public boolean validateTransactionAmount(long debitAcc, double transferAmount) throws DaoException {
		String sql = "select  balance from accounts where  account_no =" + debitAcc;
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (DBUtilityException e1) {
			throw new DaoException(e1.getMessage(), e1);
		}
		Statement st = null;
		try {
			st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);
			double balance = 0;
			while (rs.next()) {
				balance = rs.getDouble(1);

			}
			if (balance < transferAmount)
				return false;
			else
				return true;
		} catch (SQLException e) {
			throw new DaoException("Unable to retrieve data");
		} finally {

			try {
				DBUtil.releaseResource(st);
				DBUtil.releaseResource(con);
			} catch (DBUtilityException e) {
				throw new DaoException(e.getMessage());
			}
		}

	}
}
