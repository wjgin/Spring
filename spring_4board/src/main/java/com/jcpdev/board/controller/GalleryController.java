package com.jcpdev.board.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcpdev.board.model.Gallery;
import com.jcpdev.board.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	GalleryService service;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String gallery(Model model) {
		
		model.addAttribute("list", service.getAll());
		
		return "gallery/gallery";
	}
	
	/*
	 * @RequestMapping(value="/save", method= RequestMethod.POST) public String
	 * fileSave(ArrayList<MultipartFile> files, String title,Model model) { // 업로드될
	 * 경로 String path = "d:\\desktop\\upload";
	 * 
	 * for(MultipartFile file : files) { // 저장할 파일 생성 File saveFile = new File(path,
	 * file.getOriginalFilename());
	 * 
	 * try { // 파일 저장 file.transferTo(saveFile); // DB에 저장 Gallery gallery = new
	 * Gallery(); gallery.setFilename(file.getOriginalFilename());
	 * gallery.setTitle(title); service.insert(gallery);
	 * 
	 * } catch(Exception e) { e.getMessage(); } }
	 * 
	 * model.addAttribute("list", service.getAll());
	 * 
	 * return "redirect:/board/gallery"; }
	 */
	
	// 강사님 코드
	@RequestMapping(value="/save",method=RequestMethod.POST)
	//		,headers = ("content-type=multipart/*"))
	public String save(Gallery gallery) { 
		try {
			service.save(gallery);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/gallery/";
	}
	
}

