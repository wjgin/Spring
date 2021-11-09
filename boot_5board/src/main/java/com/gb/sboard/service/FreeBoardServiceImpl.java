package com.gb.sboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.gb.sboard.dto.Board;
import com.gb.sboard.dto.PageDTO;
import com.gb.sboard.mapper.FreeboardMapper;



@Service
public class FreeBoardServiceImpl implements FreeboardService{
	
	@Autowired
	FreeboardMapper dao;   //dao 는 bean (클래스의 객체)
	
	@Override
	public int insert(Board dto) {
		return dao.insert(dto);
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}

	@Override
	public List<Board> getPagelist(PageDTO dto) {
		return dao.getPagelist(dto);
	}

	@Override
	public int delete(int idx) {
		return dao.delete(idx);
	}

	@Override
	public Board getBoardOne(int idx) {
		return dao.getBoardOne(idx);
	}

	@Override
	public int update(Board dto) {
		return dao.update(dto);
	}

	@Override
	public int updateReadCnt(int idx) {
		// TODO Auto-generated method stub
		return dao.updateReadCnt(idx);
	}
	
	@Override
//	public int searchCount(Map<String,String> map) {
	public int searchCount(ModelMap map) {
		//paging에 검색을 위한 필드와 검색어 속성 포함
		Map<String,String> daomap = new HashMap<String, String>();
		//ModelMap -> HashMap
		daomap.put("field",(String) map.get("field"));
		daomap.put("findText",(String) map.get("findText"));
		return dao.searchCount(daomap);
	}
	
	@Override
	public List<Board> searchList(PageDTO dto) {
		//paging에 검색을 위한 필드와 검색어 속성 포함
		return dao.searchList(dto);
	}
	
	@Override
	public List<Board> makePageList(PageDTO pageDto){
		List<Board> list ;
		
		if(pageDto.getFindText()!=null) {
			list = searchList(pageDto);
		}else {
			list = getPagelist(pageDto);
		}
		
		return list;
	}

}
