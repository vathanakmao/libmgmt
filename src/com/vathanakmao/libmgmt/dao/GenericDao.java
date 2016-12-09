package com.vathanakmao.libmgmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public abstract class GenericDao<T, ID> {
	private RowMapper<T> rowMapper;
	
	protected abstract String getTableName();
	protected abstract String generateInsertSql(T e);
	
	protected GenericDao(RowMapper<T> rowMapper) {
		this.rowMapper = rowMapper;
	}
	
	protected RowMapper<T> getRowMapper() {
		return rowMapper;
	}
	
	protected String generateSqlSelectById(ID id) {
		StringBuffer sql = new StringBuffer("select * from ").append(getTableName());
		if (id instanceof String) {
			sql.append(" where id='").append(id).append("'");
		} else if (id instanceof Long || id instanceof Integer) {
			sql.append("where id=").append(id);
		} else {
			throw new IllegalArgumentException(id + " is of unmapped type");
		}
		return sql.toString();
	}
	
	public Connection getConnection() throws SQLException {
	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", "root");
	    connectionProps.put("password", "root");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libmgmt", connectionProps);
	    return conn;
	}
	
	public T getById(ID id) throws SQLException {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(generateSqlSelectById(id));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				T pojo = getRowMapper().toPojo(rs);
				return pojo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close Statement and Connection
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public void save(T e) throws SQLException {
		String query = generateInsertSql(e);
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.execute();
		conn.close();
	}
	
}
