package com.jcpdev.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jcpdev.board.dao.GalleryMapper;
import com.jcpdev.board.model.Gallery;

@Service
public class GalleryService {
	@Autowired
	GalleryMapper gdao;
	
	public List<Gallery> getAll() {
		return gdao.getAll();
	}
	
	public int insert(Gallery gallery) {
		return gdao.insert(gallery);
	}
	
	
	public int save(Gallery vo) throws IllegalStateException, IOException {
	      List<MultipartFile> files = vo.getFiles();
	      /*
	       * log.info("name: " + file.getOriginalFilename());
	            log.info("size: "+ file.getSize());
	       */
	      StringBuilder sb = new StringBuilder();
	      String path = "d:\\desktop\\upload";

	      if (files != null && files.size() > 0) {
	            for (MultipartFile f : files) {
	               
	               String newpath="";
	               String fileName = "board_" + f.getOriginalFilename();   //board_원래 파일명.확장자
	               if (!fileName.equals("")) {
	                  newpath = path + "\\" + fileName;   //업로드경로+파일명
	                  sb.append(fileName).append(",");
	                  // 선택한 파일을 서버로 전송
	                  File upfile = new File(newpath);
	                  f.transferTo(upfile);
	               }
	            }
	            vo.setFilename(sb.toString());
	         }

	      return gdao.insert(vo);
	   }
}
