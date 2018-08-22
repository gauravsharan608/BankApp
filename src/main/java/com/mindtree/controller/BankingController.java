package com.mindtree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mindtree.entity.*;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.InvalidInputException;
import com.mindtree.exceptions.PersistException;
import com.mindtree.service.BankServiceImpl;

/**
 * Servlet implementation class BankingControllerMock
 */
public class BankingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BankingController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getRequestURI().equals("/BankApp/transferFund")) {
			displayTransferPage(request, response);
		}
		if (request.getRequestURI().equals("/BankApp/getAccountNumber")) {
			getBeneficiaryAccNumber(request, response);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 *             This method fetches account number of benficiary in return to
	 *             the ame that itr eceives as its argumant. This method is
	 *             invoked through Ajax.
	 */
	private void getBeneficiaryAccNumber(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String name = request.getParameter("name");
		PrintWriter writer = response.getWriter();
		if (name == "select" || name == "" || name == null) {
			writer.print("");
		} else {
			Long accNo = 0L;
			try {
				accNo = new BankServiceImpl().getAccountNumber(name);
			} catch (FetchException e) {
				
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/errorPage.jsp").forward(request,
						response);
				return;
			}
			request.getServletContext().setAttribute("dest", accNo);
			System.out.println(accNo);
			writer.print(accNo);
			writer.close();
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             This method fetches from the database, the list of available
	 *             accounts and te available beneficiaries. Then it passs these
	 *             colelctions to the JSP page .
	 */
	private void displayTransferPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		List<Account> list = new ArrayList<Account>();
		List<String> beneficiaries = new ArrayList<String>();

		if (null != session.getAttribute("user")) {

			String username = (String) request.getServletContext()
					.getAttribute("username");
			try {

				list = new BankServiceImpl().fetchAccount(username);

			} catch (FetchException | InvalidInputException e) {
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/errorPage.jsp").forward(request,
						response);
				return;
			}

			try {
				beneficiaries = new BankServiceImpl().getBenficiaries();
			} catch (FetchException e) {
				// TODO Auto-generated catch block
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/errorPage.jsp").forward(request,
						response);
				return;
			}

		}
		if (list.size() > 0 && beneficiaries.size() > 0) {
			
			request.setAttribute("beneficiaries", beneficiaries);
			request.setAttribute("accounts", list);
			request.getRequestDispatcher("/transferPage.jsp").forward(request,
					response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      This method is invoked if the HTTP request method is "POST".
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getRequestURI().equals("/BankApp/processTransfer")) {
			System.out.println("In doPost");
			processTransaction(request, response);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             This method is the key functionary for performing the
	 *             transaction, i.e. to transfer a sum of money from an account
	 *             to a beneficiary account.
	 */
	private void processTransaction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);
		boolean transferred = false;

		if (null != session.getAttribute("user")) {
			Long from = Long.parseLong(request.getParameter("fromAc"));

			long to = (long) request.getServletContext().getAttribute("dest");

			double transactionAmount = Double.parseDouble(request
					.getParameter("amount"));

			String detail = request.getParameter("detail");

			Account debitAccount = new Account(from, 0, null);
			Account creditAccount = new Account(to, 0, null);
			Transaction transaction = new Transaction(0, debitAccount,
					creditAccount, transactionAmount, new java.util.Date(),
					detail);

			try {
				transferred = new BankServiceImpl().transferFund(transaction);
			} catch (PersistException | InvalidInputException e) {
				request.setAttribute("message", e.getMessage());
				request.getRequestDispatcher("/errorPage.jsp").forward(request,
						response);
				return;
			}

		}
		if (transferred) {

			request.getServletContext().setAttribute("transactionSuccess",
					"Transaction Details added successfully");
			request.getRequestDispatcher("/transferSuccess.jsp").forward(
					request, response);

		}

	}
}
