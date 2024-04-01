package com.ssafy.tv.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.mem.util.DBUtil;
import com.ssafy.tv.model.dto.TvDto;

public class TvDaoImpl implements TvDao{

	private static TvDao instance = new TvDaoImpl();
	private DBUtil dbUtil;
	
	private TvDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static TvDao getInstance() {
		return instance;
	}

	@Override
	public List<TvDto> list() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TvDto> list = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from tv");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			list = new ArrayList<TvDto>();
			while(rs.next()) {
				TvDto dto = new TvDto();
				dto.setPn(rs.getInt("pn"));
				dto.setName(rs.getString("name"));
				dto.setBrand(rs.getString("brand"));
				dto.setPrice(rs.getInt("price"));
				list.add(dto);
			}
			return list;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public void regist(TvDto dto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into tv(pn, name, brand, price) \n");
			sql.append("values(?,?,?,?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getPn());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getBrand());
			pstmt.setInt(4, dto.getPrice());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public TvDto detail(Integer pn) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from tv \n");
			sql.append("where pn = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, pn);
			rs = pstmt.executeQuery();
			TvDto dto = new TvDto();
			while(rs.next()) {
				dto.setPn(rs.getInt("pn"));
				dto.setName(rs.getString("name"));
				dto.setBrand(rs.getString("brand"));
				dto.setPrice(rs.getInt("price"));
			}
			return dto;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public void update(TvDto dto) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update tv \n");
			sql.append("set name=?, brand=?, price=? \n");
			sql.append("where pn=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBrand());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, dto.getPn());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public void delete(Integer pn) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from tv \n");
			sql.append("where pn=?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, pn);
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
		
	}
}
