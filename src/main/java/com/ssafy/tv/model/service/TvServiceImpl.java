package com.ssafy.tv.model.service;

import java.util.List;

import com.ssafy.tv.model.dao.TvDao;
import com.ssafy.tv.model.dao.TvDaoImpl;
import com.ssafy.tv.model.dto.TvDto;

public class TvServiceImpl implements TvService {

	private static TvService instance = new TvServiceImpl();
	private TvDao dao;
	private TvServiceImpl() {
		dao = TvDaoImpl.getInstance();
	}
	public static TvService getInstance() {
		return instance;
	}
	@Override
	public List<TvDto> list() throws Exception {
		return dao.list();
	}
	@Override
	public void regist(TvDto dto) throws Exception {
		dao.regist(dto);
	}
	@Override
	public TvDto detail(Integer pn) throws Exception {
		return dao.detail(pn);
	}
	@Override
	public void update(TvDto dto) throws Exception {
		dao.update(dto);
		
	}
	@Override
	public void delete(Integer pn) throws Exception {
		dao.delete(pn);
		
	}
}
