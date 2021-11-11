package com.jcpdev.board.service;

import java.util.Optional;

import com.jcpdev.board.dto.BoardDTO;
import com.jcpdev.board.entity.Board;
import com.jcpdev.board.entity.Users;

public interface BoardService {
	Long register(BoardDTO dto);
	
	
	default Board toEntity(BoardDTO dto) {
		Users member = Users.builder().mno(dto.getWriterMno()).build();
		
		Board board = Board.builder().bidx(dto.getBidx()).subject(dto.getSubject()).content(dto.getContent())
				.writer(member).build();
		return board;		
	}
	
	default BoardDTO toDto(Board b) {
		BoardDTO dto = BoardDTO.builder()
				.bidx(b.getBidx())
				.subject(b.getSubject())
				.content(b.getContent())
				.writerMno(b.getWriter().getMno())
				.writerName(b.getWriter().getName())
				.regDate(b.getRegDate())
				.modDate(b.getModDate())
				.build();
		return dto;
	}
	
}
