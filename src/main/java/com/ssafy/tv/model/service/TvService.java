package com.ssafy.tv.model.service;

import java.util.List;

import com.ssafy.tv.model.dto.TvDto;

public interface TvService {

	List<TvDto> list() throws Exception;

	void regist(TvDto dto) throws Exception;

	TvDto detail(Integer pn) throws Exception;

	void update(TvDto dto) throws Exception;

	void delete(Integer pn)throws Exception;

}
