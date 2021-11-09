package com.gb.sboard.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.gb.sboard.dto.Board;
import com.gb.sboard.dto.PageDTO;


public interface FreeboardService {
	//서비스 인터페이스에서는 dao의 메소드들을 구현하고 session,request등을 제어하는 메소드 설계 작성
	int insert(Board dto);
	int getCount();
	List<Board> getPagelist(PageDTO dto);
	int delete(int idx);
	Board getBoardOne(int idx);
	int update(Board dto);
	int searchCount(ModelMap map);
//	int searchCount(Map<String,String> map);
	List<Board> searchList(PageDTO dto);
	
	List<Board> makePageList(PageDTO pageDto);
	int updateReadCnt(int idx);
}
