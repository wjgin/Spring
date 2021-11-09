package com.gb.sboard.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gb.sboard.dto.Board;
import com.gb.sboard.dto.PageDTO;
import com.gb.sboard.service.CommentService;
import com.gb.sboard.service.FreeboardService;

import lombok.extern.log4j.Log4j2;


@Controller
@RequestMapping("/community")
@Log4j2
public class FreeboardController {
	
	@Autowired
	FreeboardService service;
	
	@Autowired
	CommentService  cmtservice;
	
	//게시판 리스트 보기(검색기능 포함)
	@RequestMapping(value={"/","/list"})
	public void pageList(String page,String field,String findText,Model model) {
		
		int currentPage;//현재 페이지
		List<Board> list;
		int totalCount; int pageSize=10;
		
		if(page==null || page.trim().length()==0) currentPage = 1;
		else currentPage = Integer.parseInt(page);   //page파라미터가 숫자로 넘어온경우만 실행. 
		
		//page를 사용자 맘대로 문자 대입하면 NumberFormatExceptrion 발생 -> ExceptionHandler 로 처리합니다.
		
		PageDTO pageDto;
		ModelMap modelMap = new ModelMap();    
		//검색 기능사용할 때 검색필드와 검색키워드 뷰에 전달한다.아래 pageDto 객체와 중복되는값.편의상.
		modelMap.put("field",field);
		modelMap.put("findText",findText);
		
		if(findText !=null) {	//검색하는 경우
			totalCount=service.searchCount(modelMap);    //서비스 메소드 타입 변경예정
//			pageDto=new PageDTO(currentPage, pageSize, totalCount, field, findText);
//			list=service.searchList(pageDto);  //주석처리 예정
		}else {   //검색안하는 경우
			totalCount=service.getCount();
//			pageDto=new PageDTO(currentPage, pageSize, totalCount, field, findText);
//			list=service.getPagelist(pageDto); //주석처리 예정
		}
		
		pageDto = new PageDTO(currentPage, pageSize, totalCount, field, findText);
		list = service.makePageList(pageDto);
		modelMap.put("page", pageDto);			//view에게 전달할 모델객체 설정
		modelMap.put("list",list);				//          "
		model.addAllAttributes(modelMap);	//위에 4개의 put 실행한 map객체를 애트리뷰트에 저장한다.
	}  //view 이름은? list.jsp
	
	
	
	
	//상세보기
	@RequestMapping("/detail")     
	public String detail(int idx, int page,Model model,@CookieValue("view") String cookie,HttpServletResponse response) {
		log.info("idx : " + idx);
						//파라미터 처리            //쿠키어노테이션으로 가져오기 : view 이름의 쿠키가 없으면 오류.
		//조회수 증가여부     String.valueOf(idx) : idx정수를 문자열로 변환
		if(cookie!=null && !cookie.contains(String.valueOf(idx))) {   //view라는 이름의 쿠키 값에 idx 가 없는가?  contains("문자열") 문자열 포함하고 있으면 참. 
			//조회수 +1 증가 서비스 호출
			cookie += "/"+idx;						//읽은 글의 idx를 cookie 문자열에 연결하기(쿠키에제한된문자,한글인코딩 확인)
			service.updateReadCnt(idx);
		}
		Cookie newcoo = new Cookie("view", cookie);
		newcoo.setMaxAge(50*60);
											//setPath 설정안하면 기본적으로 현재 위치 http://localhost:8085/freeboard/commnunity
		newcoo.setPath("/");       //쿠키의 적용범위  --> http://localhost:8085/freeboard
		response.addCookie(newcoo);    //새로만들어진 cookie 문자열 결과를 쿠키에 저장하기
		//servie 메소드 호출
		//view에 리턴받은 DTO를 전달
		model.addAttribute("bean", service.getBoardOne(idx));
		//list 에서 전달받은 현재페이지를 다시 detail.jsp에게도 전달  -> 목록이동시에 원래보던 페이지로 이동
		model.addAttribute("page", page);
		//댓글 리스트 가져오기 -> model 객체에 저장한다.
		model.addAttribute("cmtlist",cmtservice.commentList(idx));
		model.addAttribute("cr", "\n");   //textarea에서 입력된 줄바꿈을 화면 출력시 <br>변환하기 위해서 필요함.
		//view 이름은? 자동으로 detail.jsp
		
		return "community/detail";
	}
	
	//글쓰기 - view  : insert() 메소드 
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public void insert(int page,Model model) {
		model.addAttribute("page", page);
	}  //view이름은 insert
	
	//글쓰기 - 저장   : save()메소드  리다이렉트 list로.
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String save(@ModelAttribute Board board) {
		service.insert(board);
		
		return "redirect:/community/list";
	}
	
	//수정
	
	
	//삭제
	
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleErr(HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", request.getRequestURL());   //애트리뷰트 저장
		mav.setViewName("/error/error");
		return mav;
	}
	
}
