package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.UserModel;

public class UserDAO {

	public static boolean checkUserLogin(String username, String password) {

		Connection connection = DBConfig.getConnection();
		String sql = "SELECT * FROM tb_user WHERE username=? and password=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static UserModel getUser(String username) {
		UserModel user = new UserModel();
		Connection connection = DBConfig.getConnection();
		String sql = "SELECT *FROM tb_user WHERE username=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				user.setFullName(resultSet.getString("fullname"));
				user.setUserName(resultSet.getString("username"));
				user.setPassWord(resultSet.getString("password"));
				user.setRoleName(resultSet.getString("rolename"));
				user.setRole(resultSet.getInt("role"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return user;
	}
}
