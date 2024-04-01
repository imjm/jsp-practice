package com.ssafy.mem.model.service;

import com.ssafy.mem.model.dto.MemDto;

public interface MemService {

	MemDto login(String id, String pw) throws Exception;

	void join(MemDto dto) throws Exception;

	boolean check(String id) throws Exception;

}
