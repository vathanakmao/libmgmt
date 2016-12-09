package com.vathanakmao.libmgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RowMapper<T> {
	
	public abstract T toPojo(ResultSet rs) throws SQLException;
}
