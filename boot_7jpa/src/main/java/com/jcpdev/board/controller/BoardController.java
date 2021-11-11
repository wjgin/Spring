package com.jcpdev.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcpdev.board.dto.BoardDTO;
import com.jcpdev.board.entity.Board;
import com.jcpdev.board.repository.BoardRepository;
import com.jcpdev.board.service.BoardService;

@RestController	//RESTful 서비스 동작을 하는 컨트롤러
public class BoardController {
	@Autowired
	BoardRepository repository;
	
	@Autowired
	BoardService service;
	
	@GetMapping("/board")
	public BoardDTO getOne(int idx) {
		Optional<Board> result = repository.findById(Long.valueOf(idx));
		
		return service.toDto(result.get());
	}
	
	@GetMapping("/board/list")
	public List<BoardDTO> getList() {
		List<Board> list = repository.findAll();
		List<BoardDTO> result = new ArrayList<>();
		list.forEach(item -> {	// 람다식
			result.add(service.toDto(item));
		});
		return result;
	}
	// 이 핸들러의 return이 view가 아님
	// 리턴이 데이터 -> HTTP 응답으로 response body에 데이터를 전송
	// 스프링부트 mvc메서드 반환값 HttpMessageConverter를 통해
	// json형식으로 전환한다.
	// 스프링 부트는 필요한 라이브러리 jackson, json을 자동으로 가져옮
}
