package com.ssafy.mem.model.dao;

import com.ssafy.mem.model.dto.MemDto;

public interface MemDao {

	MemDto login(String id, String pw) throws Exception;

	void join(MemDto dto)throws Exception;

	boolean check(String id) throws Exception;

}
