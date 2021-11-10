package com.jcpdev.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcpdev.board.model.Gallery;
import com.jcpdev.board.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	GalleryService service;
	
	public GalleryController(GalleryService service) {
		this.service = service;
	}
	 
	
	@RequestMapping(value ="/gallery", method = RequestMethod.GET)
	public String gallery(Model model) {
		List<Gallery> list =  service.getAll();
		model.addAttribute("list",list);
		return "gallery/gallery";
	}
	
	@RequestMapping(value = "/gallery", method = RequestMethod.POST)
	public String save(Gallery vo) {
		System.out.println(vo);
		try {
			service.save(vo);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}

}
