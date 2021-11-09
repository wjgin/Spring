package com.gb.sboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.sboard.dto.Comment;
import com.gb.sboard.mapper.CommentMapper;


@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	CommentMapper cdao;
	
	
	@Override
	public int insert(Comment dto) {
		// TODO Auto-generated method stub
		return cdao.insert(dto);
	}

	@Override
	public List<Comment> commentList(int mref) {
		// TODO Auto-generated method stub
		return cdao.commentList(mref);
	}

	@Override
	public int commentCount(int mref) {
		// TODO Auto-generated method stub
		return cdao.commentCount(mref);
	}

	@Override
	public int update(int idx,String content) {
		// sql mapper에게 전달할 Map 객체 생성합니다.
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("idx",idx);
		map.put("content",content);
		return cdao.update(map);
	}

	@Override
	public int delete(int idx) {
		// TODO Auto-generated method stub
		return cdao.delete(idx);
	}

	@Override
	public int updateCommentCnt(int mref) {
		// TODO Auto-generated method stub
		return cdao.updateCommentCnt(mref);
	}

	
	
}
