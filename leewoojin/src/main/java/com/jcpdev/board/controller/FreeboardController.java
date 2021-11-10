package  com.jcpdev.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jcpdev.board.model.Board;
import com.jcpdev.board.model.PageDto;
import com.jcpdev.board.service.FreeboardService;

@Controller
@RequestMapping("/community") //위에다 맵핑 써놓으면 view 폴더명 동일할때 생략 , 리다이렉트 community 생략 가능
public class FreeboardController {
	private static final Logger logger = LoggerFactory.getLogger(FreeboardController.class);
	
	@Autowired
	FreeboardService service;
	

	
	
	//게시판 리스트 보기(검색기능 포함) 	request mapping을 여러 url 요청으로 할 수 있다 value가 배열
	@RequestMapping(value={"/","/list"})
	public String pageList(@RequestParam Map<String, Object> param,Model model) { //String page,String field,String findText,Model model) {
		logger.info("**freeboard list 출력합니다.");
		int currentPage;//현재 페이지
		List<Board> list;
		int totalCount; int pageSize=10;
		String page=(String) param.get("page");
		if(page==null || page.trim().length()==0) currentPage = 1;
		else currentPage = Integer.parseInt(page);   //page파라미터가 숫자로 넘어온경우만 실행. 
		
		//page를 사용자 맘대로 문자 대입하면 NumberFormatExceptrion 발생 -> ExceptionHandler 로 처리합니다.
		
		PageDto pageDto;
		//검색 기능사용할 때 검색필드와 검색키워드 뷰에 전달한다.

		String findText = (String) param.get("findText");
		String field=(String) param.get("field");
		
		totalCount=service.searchCount(param);   //서비스 메소드 타입 변경예정
		pageDto=new PageDto(currentPage, pageSize, totalCount, field, findText);
		list=service.searchList(pageDto);  //주석처리 예정
		Map<String,Object> map = new HashMap<String,Object>();    
		map.put("field",field);
		map.put("findText",findText);
		map.put("page", pageDto);			//view에게 전달할 모델객체 설정
		map.put("list",list);				//          "
		model.addAllAttributes(map);	//위에 4개의 put 실행한 map객체를 애트리뷰트에 저장한다.
		
		return "community/list";
	}  //view 이름은? list.jsp
	
	
	//페이지 리스트 만드는 부분을 서비스로 이동시키는 예제
	@RequestMapping(value = "/list2")
	public String list2(@RequestParam Map<String, Object> param,Model model) {
		//컨트롤러는 요청에 대한 데이터 처리를 서비스로 전달하고 결과를 리턴받으면
		//모델에 저장해서 view로 전달한다.
		model.addAllAttributes(service.searchList2(param));
		return "community/list";
	}
	
	
	
	
	@RequestMapping("/detail")     
	//public void detail(@RequestParam Map<String, Object> param , Model model) {
		public String detail(@RequestParam Map<String, Object> param , Model model
				,HttpServletResponse response
				,@CookieValue(name="read",defaultValue = "abcde") String readidx) {
		//읽어올 쿠키이름은 read 쿠키 값이 없다면 기본값 "abcde" 
		//쿠키값을 저장할 변수는 readidx default값이 없으면 처음실행시 쿠키값없어서 오류남
		
		//받는곳
		int idx = Integer.parseInt((String) param.get("idx"));
		int mref = idx;
		Board bean = service.getBoardOne(idx);
		
		if(!readidx.contains(String.valueOf(idx))) {//idx 정수값을 String으로 변환
			//읽지 않은 글
			readidx += "/" + idx;	//idx가 캐스팅되는 것은 아님
			//조회수 증가 메소드
			service.updateReadCnt(idx);
		}
		
		//쿠키값 없었을 때 또는 새로 변경되었을 때
		Cookie cookie = new Cookie("read",readidx);
		//쿠키 유효시간 설정, 쿠키 경로 설정
		cookie.setMaxAge(60*30);	//초 단위, 30분 설정
		cookie.setPath("/board");
		response.addCookie(cookie);	//기존 쿠키 정보에 쿠키 항목 추가
		//쿠키가 HttpOnly 속성을 true -> 클라이언트 단에서는 쓰기 안됨. secure 속성은 암호화해서 전송 https 프로토콜 통신으로만 사용
		//주는곳
		param.put("bean", bean); //인자로 받은 맵에 추가
		param.put("cr", "\n");
		model.addAllAttributes(param);	

		return "community/detail";
	}
	
	//글쓰기 - view  : insert() 메소드 
	@RequestMapping(value="/insert")
	public void insert(int page, Model model) {
		model.addAttribute("page", page);
	}  //view이름은 insert
	
	//글쓰기 - 저장   : save()메소드  리다이렉트 list로.
	@RequestMapping(value="/save")
	public String save(@ModelAttribute Board board) {
	//@ModelAttribute 생략 가능 : form 입력 -> @ModelAttribute -> 컨트롤러
		service.insert(board);
		
		return "redirect:/community/list";
		// redirect할 때 , /가 없으면 현재 경로에서 접근
		// /가 있으면 contextPath /board 에서 시작
		// "redirect:community/list" 는 오류
		// "redirect:/community/list" 는 정상
	}
	
	//수정
	@RequestMapping(value = "update", method = RequestMethod.GET) //update.jsp 로 들어갈때
	public void update(@RequestParam Map<String, String> param,Model model) {		//@RequestParam Map<String, String> param
		model.addAttribute("bean", service.getBoardOne(Integer.parseInt(param.get("idx"))));
		model.addAttribute("page",param.get("page"));
		model.addAttribute("field",param.get("field"));
		model.addAttribute("findText",param.get("findText"));
	}
	
	//수정 내용 저장
	@RequestMapping(value = "update" , method = RequestMethod.POST) //update.jsp에서 수정 저장할때
	public String save2(@ModelAttribute Board board,int idx,int page,String field,String findText, Model model) {//@ModelAttribute 생략됨
		service.update(board);
		model.addAttribute("idx",idx);
		model.addAttribute("page",page);
		model.addAttribute("field",field);
		model.addAttribute("findText",findText);
		return "redirect:list";
	}
	
	@RequestMapping(value="delete")
	public String delete(@RequestParam Map<String,Object> param,Model model) {
		service.delete(Integer.parseInt((String)param.get("idx")));
		//model.addAttribute("page", page);
		model.addAllAttributes(param);
		return "redirect:list";
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleErr(HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", request.getRequestURL());   //애트리뷰트 저장
		mav.setViewName("/error/error");
		return mav;
	}
	
	
	
}
