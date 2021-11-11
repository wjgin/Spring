package com.jcpdev.board.service;

import org.springframework.stereotype.Service;

import com.jcpdev.board.dto.BoardDTO;
import com.jcpdev.board.entity.Board;
import com.jcpdev.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor		//final 필드와 @NoNull 필드에 대해 생성자 주입
//@Log4j2
public class BoardServiceImpl implements BoardService{
	private final BoardRepository repository;
	
	@Override
	public Long register(BoardDTO dto) {

		Board board = toEntity(dto);
		repository.save(board);
		return board.getBidx();
	}
	
}
