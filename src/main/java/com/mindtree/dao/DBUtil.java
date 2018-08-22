/**
 * 
 */
package com.mindtree.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.mindtree.exceptions.DBUtilityException;

/**
 * @author M1030608
 *
 *         This class provides utility methods for establishing and releasing
 *         database connection. It provides methods to release various resources
 *         used in database connection.
 */

public class DBUtil {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/BANK_DB";
	private static final String USER = "root";
	private static final String PWD = "Welcome123";

	/**
	 * private default constructor to prevent instantiation of the utility class
	 */
	private DBUtil() {
	}

	/**
	 * Static block to initialize the mYSQL driver. The driver is initialized
	 * before the class loads
	 */
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return database connection
	 * @throws DBUtilityException
	 *             This method is meant to establish database connection, and
	 *             returns the Connection object after successfully establishing
	 *             the connection.
	 */
	public static Connection getConnection() throws DBUtilityException {
		try {
			return DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {
			throw new DBUtilityException("Error in establishing connection", e);
		}
	}

	/**
	 * 
	 * @param st
	 * @throws DBUtilityException
	 *             This method is meant to release the instance of Statement
	 *             class.
	 */
	public static void releaseResource(Statement st) throws DBUtilityException {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBUtilityException("Error in releasing Statement", e);
			}
		}
	}

	/**
	 * 
	 * @param ps
	 * @throws DBUtilityException
	 *             This method is meant to release the instance of
	 *             PreparedStatement class.
	 */
	public static void releaseResource(PreparedStatement ps)
			throws DBUtilityException {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBUtilityException("Error in releasing PS", e);
			}
		}
	}

	/**
	 * 
	 * @param con
	 * @throws DBUtilityException
	 *             This method is meant to release the instance of Connection
	 *             class.
	 */
	public static void releaseResource(Connection con)
			throws DBUtilityException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DBUtilityException("Error in releasing Connection", e);
			}
		}
	}

}
