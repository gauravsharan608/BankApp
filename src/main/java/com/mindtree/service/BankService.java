/**
 * 
 */
package com.mindtree.service;

import java.util.List;

import com.mindtree.entity.Account;
import com.mindtree.entity.Customer;
import com.mindtree.entity.Transaction;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.InvalidInputException;
import com.mindtree.exceptions.PersistException;

/**
 * @author M1030608
 *
 */
public interface BankService {

	boolean validateLogin(Customer customer) throws FetchException;

	boolean transferFund(Transaction t) throws PersistException, InvalidInputException;

	List<Account> fetchAccount(String username) throws FetchException, InvalidInputException;

	

}
