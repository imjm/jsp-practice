package com.ssafy.mem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.catalina.tribes.membership.MemberImpl;

import com.ssafy.mem.model.dto.MemDto;
import com.ssafy.mem.util.DBUtil;

public class MemDaoImpl implements MemDao{

	private static MemDao instance = new MemDaoImpl();
	private DBUtil dbUtil;
	private MemDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	public static MemDao getInstance() {
		return instance;
	}
	@Override
	public MemDto login(String id, String pw) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from members where id=? and pw=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			MemDto dto = new MemDto();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
			}
			return dto;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	@Override
	public void join(MemDto dto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into members(id, pw, name, age) \n");
			sql.append("values(?,?,?,?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getAge());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}
	@Override
	public boolean check(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from members where id=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
}
