package DBConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

public class DBConfig {

	private static Connection conn;
	private static ResultSet rs;
	private static PreparedStatement pst;
	private static Statement stmt;
	private static CallableStatement callst;

	private static DBConfig db = null;

	private DBConfig() {
		try {

			Properties p = new Properties();
			p.load(PathHelper.fin);
			String driver = p.getProperty("driver");
			String uname = p.getProperty("username");
			String pass = p.getProperty("password");
			String url = p.getProperty("url");

			Class.forName(driver);
			conn = DriverManager.getConnection(url, uname, pass);
			if (conn != null) {
				System.out.println("Connection established Successfully");
			} else {
				System.out.println("Connection not established");
			}
		} catch (Exception ex) {
			System.out.println("Exception in DBConfig");
			System.out.println(ex);
		}

	}

	public static DBConfig getConnection() {
		if (db == null) {

			db = new DBConfig();

		}
		return db;
	}

	public static Connection getCon() {
		return conn;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static PreparedStatement getPst() {
		return pst;
	}

	public static Statement getStmt() {
		return stmt;
	}

	public static CallableStatement getCallst() {
		return callst;
	}

}
