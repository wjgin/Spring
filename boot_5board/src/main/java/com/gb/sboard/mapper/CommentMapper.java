package com.gb.sboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.gb.sboard.dto.Comment;

@Repository
@Mapper
public interface CommentMapper {
	int insert(Comment dto);
	List<Comment> commentList(int mref);
	int commentCount(int mref);
	int update(Map<String,Object> map);
	int delete(int idx);
	int updateCommentCnt(int idx);   //freeboard 테이블 댓글갯수 증가 update
}
