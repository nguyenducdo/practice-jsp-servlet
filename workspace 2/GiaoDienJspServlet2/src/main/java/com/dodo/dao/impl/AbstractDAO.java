package com.dodo.dao.impl;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.dodo.dao.GenericDAO;
import com.dodo.mapper.IRowMapper;
import com.dodo.utils.DBUtils;

public abstract class AbstractDAO<T> implements GenericDAO<T> {

	private void setParameter(PreparedStatement ps, Object... params) {
		try {
			int index = 1;
			for (Object param : params) {
				if (param == null) {
					ps.setNull(index++, Types.NULL); //NULL
				}else if (param instanceof Long) {
					ps.setLong(index++, (long) param); //long
				}else if(param instanceof String) {
					ps.setString(index++, (String) param); //String
				}else if(param instanceof Integer) {
					ps.setInt(index++, (Integer) param); //int
				}else if(param instanceof Float) {
					ps.setFloat(index++, (Float) param); //float
				}else if(param instanceof Timestamp) {
					ps.setTimestamp(index++, (Timestamp) param); //time
				}else if(param instanceof Blob) {
					ps.setBlob(index++, (Blob) param); //blob
				}else if(param instanceof InputStream) {
					ps.setBlob(index++, (InputStream) param); //inputstream
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<T> query(String sql, IRowMapper<T> mapper, Object... params) {
		List<T> list = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			if (conn == null) return null;
			ps = conn.prepareStatement(sql);
			setParameter(ps, params);
			rs = ps.executeQuery();
			while (rs.next()) {
				T element = mapper.mapRow(rs);
				list.add(element);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			list = null;
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return list;
	}

	@Override
	public Long save(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Long key = null;
		try {
			conn = DBUtils.getConnection();
			if(conn==null) return null;
			ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			setParameter(ps, params);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				key = rs.getLong(1);
			}
			conn.commit();
		}catch(SQLException e) {
			DBUtils.rollback(conn);
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return key;
	}

	@Override
	public void modify(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConnection();
			if(conn==null) return;
			ps = conn.prepareStatement(sql);
			setParameter(ps, params);
			ps.executeUpdate();
			conn.commit();
		}catch(SQLException e) {
			DBUtils.rollback(conn);
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
	}

	@Override
	public Integer count(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer result = null;
		try {
			conn = DBUtils.getConnection();
			if(conn==null) return null;
			ps = conn.prepareStatement(sql);
			setParameter(ps, params);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return result;
	}


}
