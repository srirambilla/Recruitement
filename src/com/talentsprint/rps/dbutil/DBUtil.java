package com.talentsprint.rps.dbutil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.talentsprint.rps.exception.RPSException;

public class DBUtil {

	static public Connection getConnection() throws RPSException {

		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
		} catch (ClassNotFoundException e) {
			throw new RPSException(e.toString());
		} catch (SQLException e) {
			throw new RPSException(e.toString());
		}
		return connection;
	}

	static public void close(Object... args) throws RPSException {

		for (Object object : args) {
			if (null != object) {

				try {
					if (object instanceof ResultSet) {
						((ResultSet) object).close();
					} else if (object instanceof CallableStatement) {
						((CallableStatement) object).close();
					} else if (object instanceof PreparedStatement) {
						((PreparedStatement) object).close();
					} else if (object instanceof Statement) {
						((Statement) object).close();
					}
				} catch (SQLException e) {
					throw new RPSException(e.toString());
				}

			}
		}

	}

}
