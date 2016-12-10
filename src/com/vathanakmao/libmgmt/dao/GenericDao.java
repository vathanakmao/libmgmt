package com.vathanakmao.libmgmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class GenericDao<T, ID> {
	private String tableName;
	private RowMapper<T> rowMapper;
	
	protected abstract String generateSqlInsert(T e);
	
	protected GenericDao(String tableName, RowMapper<T> rowMapper) {
		this.tableName = tableName;
		this.rowMapper = rowMapper;
	}
	
	protected String getTableName() {
		return tableName;
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
	
	protected String generateSqlSelectWhereColumnEquals(String colName, Object colValue) {
		StringBuffer sql = new StringBuffer("select * from ").append(getTableName());
		if (colValue instanceof String) {
			sql.append(" where ").append(colName).append("='").append(colValue).append("'");
		} else if (colValue instanceof Long || colValue instanceof Integer) {
			sql.append("where ").append(colName).append("=").append(colValue);
		} else {
			throw new IllegalArgumentException(colValue + " is of unmapped type");
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
	
	public List<T> getBy(String columnName, Object columnValue) throws SQLException {
		List<T> result = new ArrayList<T>();
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(generateSqlSelectWhereColumnEquals(columnName, columnValue));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				T pojo = getRowMapper().toPojo(rs);
				result.add(pojo);
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
		return result;
	}
	
	public void save(T e) throws SQLException {
		String query = generateSqlInsert(e);
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.execute();
		conn.close();
	}
	
}
