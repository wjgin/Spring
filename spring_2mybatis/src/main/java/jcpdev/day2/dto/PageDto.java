package jcpdev.day2.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDto {
	//생성자로 전달받을 값.
	private int currentPage;
	private int pageSize;
	private int totalCount;
	
	//계산이 필요한 값
	private int startNo;
	private int totalPage;
	private int startPage;
	private int endPage;
	
	//검색에 필요한 값
	private String field;
	private String findText;
	
	//생성자에서 필수 속성 3개 초기화 , 나머지 값 계산
	public PageDto(int currentPage, int pageSize, 
			int totalCount,String field ,String findText) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		
		totalPage = (totalCount-1)/pageSize +1;     //페이지 갯수 (정수/정수는 정수)
		//전체 페이지 갯수 계산 예1) totalCount 81 -> 페이지 갯수는 9개 , 예2) totalCount 9개 -> 페이지갯수 1개
		
		this.currentPage = (currentPage>totalPage || currentPage < 1)? 1:currentPage;
		startNo = (currentPage-1) * pageSize;
		//오라클에서는 endNo가 필요하다.  startNo + pageSize -1
		//startPage, endPage 는 나중에 수식 추가합시다.
		
		this.field = field;
		this.findText=findText;
	}
}
