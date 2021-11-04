package com.jcpdev.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jcpdev.board.model.Board;
import com.jcpdev.board.model.PageDto;

@Component
public interface FreeboardMapper {
	// 서비스 인터페이스에서는 dao의 메소드들을 구현하고 session,request등을 제어하는 메소드 설계 작성
	int getCount(); // 전체 게시글 카운트

	List<Board> getAll(); // 전체 게시글

	List<Board> getPagelist(PageDto dto); // 한페이지 게시글

	int insert(Board dto); // insert

	int delete(int idx); // delete

	Board getBoardOne(int idx); // 한개 검색

	int update(Board dto); // update

	void updateReadCnt(int idx);

	int searchCount(Map<String, String> map); // 검색 카운트

	List<Board> searchList(PageDto dto); // 검색 게시글
}
