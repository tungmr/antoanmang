package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_blog?autoReconnect=true&useSSL=false", "root", "123456789");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		System.out.println(getConnection());
	}

}