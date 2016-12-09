package com.vathanakmao.libmgmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public abstract class GenericDao<T> {
	protected abstract String getTableName();
	protected abstract String generateInsertSql(T e);
	
	public Connection getConnection() throws SQLException {
	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", "root");
	    connectionProps.put("password", "root");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libmgmt", connectionProps);
	    return conn;
	}
	
	public void save(T e) throws SQLException {
		String query = generateInsertSql(e);
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.execute();
		conn.close();
	}
	
}
