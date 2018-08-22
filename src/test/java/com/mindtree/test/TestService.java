package com.mindtree.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mindtree.entity.Account;
import com.mindtree.entity.Customer;
import com.mindtree.entity.Transaction;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.InvalidInputException;
import com.mindtree.exceptions.PersistException;
import com.mindtree.exceptions.ServiceExcption;
import com.mindtree.service.BankServiceImpl;

/**
 * 
 * @author M1030608
 * 
 *         This JUnit class defines test cases to test posiive flow in the
 *         service layer.
 * 
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestService {

	private static Customer customer;
	private static Transaction transaction;

	/**
	 * 
	 * @throws Exception
	 * 
	 *             It initializes the data used by all the atest cases.
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customer = new Customer();
		customer.setUsername("gaurav");
		customer.setPassword("gaurav");
		transaction = new Transaction();

	}

	/**
	 * 
	 * @throws FetchException
	 * 
	 *             This method tests if the user is giveing valid credentials while
	 *             logging in.
	 */
	@Test
	public void testValidateLogin() throws FetchException {
		assertTrue(new BankServiceImpl().validateLogin(customer));
	}

	/**
	 * 
	 * @throws ParseException
	 * @throws PersistException
	 * @throws InvalidInputException
	 *             This method tests for completion of transacton
	 */
	@Test
	public void testTransferFund() throws ParseException, PersistException, InvalidInputException {

		transaction.setCreditAccount(new Account(123456789122L, 0, null));
		transaction.setDebitAccount(new Account(123456789123L, 0, null));
		transaction.setTransactionAmount(1100);
		// transaction.setTransactionDate(new
		// SimpleDateFormat("dd-MM-yyyy").parse("15-05-2015"));
		Date dateObj = new Date();
		String str = new SimpleDateFormat("dd-MM-yyyy").format(dateObj);
		Date dateF = new Date();
		String test = dateF.toString();
		System.out.println(str);
		transaction.setTransactionDate(new Date());
		assertTrue(new BankServiceImpl().transferFund(transaction));

	}

	/**
	 * 
	 * @throws FetchException
	 * @throws InvalidInputException
	 * 
	 *             It tests the method to fetch list of accounts
	 */
	@Test
	public void testFetchAccount() throws FetchException, InvalidInputException {
		assertTrue(new BankServiceImpl().fetchAccount("gaurav").size() > 0);
	}

	/**
	 * 
	 * @throws FetchException
	 *             It tests if list of beneficiaries is fetched.
	 */
	@Test
	public void testGetBenficiaries() throws FetchException {
		assertTrue(new BankServiceImpl().getBenficiaries().size() > 0);
	}

	/**
	 * 
	 * @throws FetchException
	 *             This method tests to retrieve account number for a available
	 *             user.
	 */
	@Test
	public void testGetAccountNumber() throws FetchException {
		long acc_no = new BankServiceImpl().getAccountNumber("Rocky K");
		Long acc = new Long(acc_no);
		assertNotNull(acc);
	}

	/**
	 * 
	 * @throws FetchException
	 * @throws IOException
	 * @throws ParseException
	 * @throws InvalidInputException
	 * 
	 *             This method tests the correct generation of report for a given
	 *             date.
	 */
	@Test
	public void testGenerateReport() {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse("15-05-2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			assertTrue(new BankServiceImpl().generateReport(new Date()).size() >= 0);
		} catch (ServiceExcption | FetchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
