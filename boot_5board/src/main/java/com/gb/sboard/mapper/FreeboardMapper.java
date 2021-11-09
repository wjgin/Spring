package com.gb.sboard.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.gb.sboard.dto.Board;
import com.gb.sboard.dto.PageDTO;

//레거시의 root-context.xml이 없기에 어노테이션 추가 필요
@Repository
@Mapper	
public interface FreeboardMapper { 
	//mapper로 dao 동작을 하도록 설정 -> spring mybatis 에서 구현체를 생성하여 bean 등록
	//sql 실행에 필요한 메소드 시그니쳐 정의
	int getCount();								//전체 게시글 카운트
	List<Board> getAll();					//전체 게시글
	List<Board> getPagelist(PageDTO dto);	//한페이지 게시글
	int insert(Board dto);					//insert
	int delete(int idx);						//delete
	Board getBoardOne(int idx);				//한개 검색
	int update(Board dto);					//update
	int searchCount(Map<String,String> map);	//검색 카운트
	List<Board> searchList(PageDTO dto);		//검색 게시글
	int updateReadCnt(int idx);
	
}









