package com.vathanakmao.libmgmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.vathanakmao.libmgmt.AppContext;

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
			sql.append(" where id=").append(id);
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
	
	protected String generateSqlSelectWhereColumnLike(String colName, Object colValue) {
		StringBuffer sql = new StringBuffer("select * from ").append(getTableName());
		if (colValue instanceof String) {
			sql.append(" where ").append(colName).append(" LIKE '%").append(colValue).append("%'");
		} else {
			throw new IllegalArgumentException(colValue + " is of unmapped type");
		}
		return sql.toString();
	}
	
	protected Connection getConnection() throws SQLException {
	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", AppContext.getInstance().getProperty("user_name"));
	    connectionProps.put("password", AppContext.getInstance().getProperty("password"));
	    
	    // Error "no suitable driver found" occurred only when running in tomcat7
	    // and this line fixed the error.
	    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	    
	    String serverName = AppContext.getInstance().getProperty("server_name");
	    String dbName = AppContext.getInstance().getProperty("database_name");
	    String portNumber = AppContext.getInstance().getProperty("port_number");
	    conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName + "?useUnicode=true", connectionProps);
	    return conn;
	}
	
	public T getById(ID id)  {
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
			throw new RuntimeException(e);
		} finally {
			// Close Statement and Connection
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public List<T> getBy(String columnName, Object columnValue) {
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
			throw new RuntimeException(e);
		} finally {
			// Close Statement and Connection
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public T getUniqueBy(String columnName, Object columnValue) {
		List<T> result = getBy(columnName, columnValue);
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	public List<T> findLike(String columnName, String columnValue) {
		List<T> result = new ArrayList<T>();
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(generateSqlSelectWhereColumnLike(columnName, columnValue));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				T pojo = getRowMapper().toPojo(rs);
				result.add(pojo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// Close Statement and Connection
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public void save(T e) {
		String query = generateSqlInsert(e);
		Connection conn = null;
		PreparedStatement stmt;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.execute();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
}
