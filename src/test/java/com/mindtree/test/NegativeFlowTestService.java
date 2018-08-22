package com.mindtree.test;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

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
 *         THis class defines test case sto test negative flows in the service
 *         layer of the application. It tests for validating a user, executing a
 *         successful transaction, and generating the report
 *
 */
public class NegativeFlowTestService {
	private static Customer customer;
	private static Transaction transaction;

	/**
	 * 
	 * @throws Exception
	 *             It initializes the data to be used by all the test cases.
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customer = new Customer();
		customer.setUsername("gaurav");
		customer.setPassword("gaurav1");
		transaction = new Transaction();

	}

	/**
	 * 
	 * @throws FetchException
	 * 
	 *             This test case tests for invalid user.
	 *
	 */
	@Test
	public void testValidateLogin() throws FetchException {
		assertFalse(new BankServiceImpl().validateLogin(customer));
	}

	/**
	 * 
	 * @throws ParseException
	 * @throws PersistException
	 * @throws InvalidInputException
	 * 
	 *             This method tests the transfer of fund, if transfer amount is
	 *             given less than available balance then it raises
	 *             invalidInputException error.
	 */
	@Test(expected = InvalidInputException.class)
	public void testTransferFund() throws ParseException, PersistException, InvalidInputException {
		transaction.setCreditAccount(new Account(123456789122L, 0, null));
		transaction.setDebitAccount(new Account(123456789123L, 0, null));
		transaction.setTransactionAmount(1100000);
		transaction.setTransactionDate(new SimpleDateFormat("dd-MM-yyyy").parse("15-05-2015"));
		new BankServiceImpl().transferFund(transaction);
	}

	@Test(expected = ServiceExcption.class)
	public void generateReport() throws ParseException, ServiceExcption {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("28/05/2018");
		try {
			new BankServiceImpl().generateReport(null);
		} catch (FetchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
