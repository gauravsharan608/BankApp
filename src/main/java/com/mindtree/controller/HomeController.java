package com.mindtree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mindtree.dao.BankDaoImpl;
import com.mindtree.entity.Customer;
import com.mindtree.entity.Transaction;
import com.mindtree.exceptions.DaoException;
import com.mindtree.exceptions.FetchException;
import com.mindtree.exceptions.InvalidInputException;
import com.mindtree.exceptions.ServiceExcption;
import com.mindtree.service.BankServiceImpl;

/**
 * Servlet implementation class HomeController This servlet controls logging in
 * and logging out of the users.
 */

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method is invoked by the servlet container. On basis of the type of
	 * HTTP request, it forwards the incoming request to doGet and doPost
	 * methods.
	 * 
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (request.getRequestURI().equals("/BankApp") && session.getAttribute("user") == null) {
			request.setAttribute("errormsg", "Invalid Access");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			if ("GET".equals(request.getMethod())) {
				doGet(request, response);
			} else if ("POST".equals(request.getMethod())) {
				doPost(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      This method is invoked if the HTTP request is GET.Then, it passes
	 *      the request to appropriate method on basis of the incoming URI
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getRequestURI().equals("/BankApp/logout")) {
			processLogout(request, response);
		} else if (request.getRequestURI().equals("/BankApp/bankHome")) {

			request.getRequestDispatcher("/home.jsp").forward(request, response);
		} else if (request.getRequestURI().equals("/BankApp/viewDetail")) {

			fetchDetailAjax(request, response);
		}
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             This method fetches report fom service layer on behalf of the
	 *             request received from client through Ajax
	 */
	private void fetchDetailAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date date = null;
		PrintWriter writer = response.getWriter();
		String dateStr = request.getParameter("id");
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
		} catch (ParseException e1) {
			writer.print("Date should be of format dd/MM/yyyy");
			writer.close();
			return;
		}

		List<Transaction> list = new ArrayList<Transaction>();

		try {
			list = new BankServiceImpl().generateReport(date);
		} catch (ServiceExcption | FetchException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
		}

		String str = "";
		if (list.size() != 0) {
			str = "<h2>Transaction Summary</h2>";

			str += "<table width=\"100%\">";

			str += "<tr id=topic><td>A/C No</td><td>Beneficiary A/C No</td><td>Transaction Amount</td></tr>";
			for (Transaction t : list) {
				str += "<tr id=content><td>" + t.getDebitAccount().getAccountNumber() + "</td><td>"
						+ t.getCreditAccount().getAccountNumber() + "</td>";
				str += "<td>" + t.getTransactionAmount() + "</td></tr>";

			}
			str += "</table>";
		}

		writer.print(str);

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 
	 *             This method is called by doGet() method. Its purpose is to
	 *             logout existing user and then it passes success message to
	 *             login page.
	 */
	private void processLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("user") != null) {
			session.invalidate();
			request.setAttribute("message", "Logged out successfully");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) This method is invoked if a user chooses to login. It
	 *      invokes processLogin() method of the same class.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getRequestURI().equals("/BankApp/login")) {
			processLogin(request, response);
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             This method creates User object by using the incoming
	 *             credentials of the user and pases the object to service layer
	 *             to validate the user. If logging in user is valid then
	 *             request is forwarded to home page.
	 * 
	 */
	private void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("userid");
		String password = request.getParameter("password");

		Customer customer = new Customer(0, "", "", username, password);
		boolean b = false;
		try {
			b = new BankServiceImpl().validateLogin(customer);
		} catch (FetchException e) {

			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
			return;

		}
		if (b) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", getCustomerName(username));
			request.getServletContext().setAttribute("username", username);
			request.setAttribute("message", "Logged in successfully");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "Invalid username or password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

	}

	/**
	 * 
	 * @param username
	 * @return
	 * 
	 * 		This method is invoked to fetch customer name for a available
	 *         username
	 */
	private Object getCustomerName(String username) {
		try {
			return new BankDaoImpl().getFullName(username);
		} catch (DaoException e) {

		}
		return username;
	}

}
