package jcpdev.day2.service;

import jcpdev.day2.dto.BoardDto;

public interface FreeboardService {
	
	// 메소드 이름은 임의로 정의합니다. 그러나 mapper와 메소드 이름을 동이랗게하는게 편함
	int getCount();
	BoardDto getBoardOne(int idx);
}
