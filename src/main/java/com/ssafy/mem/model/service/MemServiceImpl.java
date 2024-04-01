package com.ssafy.mem.model.service;

import com.ssafy.mem.model.dao.MemDao;
import com.ssafy.mem.model.dao.MemDaoImpl;
import com.ssafy.mem.model.dto.MemDto;

public class MemServiceImpl implements MemService {

	private static MemService instance = new MemServiceImpl();
	private MemDao dao;
	private MemServiceImpl() {
		dao = MemDaoImpl.getInstance();
	}
	public static MemService getInstance() {
		return instance;
	}
	@Override
	public MemDto login(String id, String pw) throws Exception {
		return dao.login(id, pw);
	}
	@Override
	public void join(MemDto dto) throws Exception {
		dao.join(dto);
		
	}
	@Override
	public boolean check(String id) throws Exception {
		return dao.check(id);
	}
}
