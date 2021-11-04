package com.jcpdev.board.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jcpdev.board.model.Comment;

@Component
public interface CommentMapper {
	int insert(Comment dto); // 댓글 입력

	List<Comment> commentList(int mref); // 게시글 전체 댓글

	int commentCount(int mref); // 게시글 전체 댓글 개수

	int update(int idx); // 댓글 수정

	int delete(int idx); // 댓글 삭제
	// int updateCmtCnt(Map<String,Integer> map); //프리보드 테이블에 댓글개수 컬럼 업데이트

	void updateCountAll(int idx);
}
