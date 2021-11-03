package jcpdev.day2.mapper;

import java.util.List;
import java.util.Map;

import jcpdev.day2.dto.BoardDto;
import jcpdev.day2.dto.PageDto;


public interface FreeboardMapper {   
	//mapper 로 dao 동작을 하도록 설정할 것입니다.-> **spring mybatis 에서 구현체를 생성하여 빈 등록합니다.**
	//sql 실행에 필요한 메소드 시그니처(프로토타입,원형) 정의. 메소드 이름은 mapper xml 의 id와 동일하게 합니다.
	int getCount();			//테이블 데이터 레코드 전체 갯수
	int insert(BoardDto dto);			//sql insert 실행
	List<BoardDto> getAll();
	List<BoardDto> getPagelist(PageDto paging);
	int delete(int idx);
	int update(BoardDto dto);
	BoardDto getBoardOne(int idx);
	//update 추가해야 됩니다.
	//검색
	int searchCount(Map<String,String> map);
	List<BoardDto> searchList(PageDto paging);
}