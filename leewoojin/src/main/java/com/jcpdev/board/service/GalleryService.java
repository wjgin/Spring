package com.jcpdev.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jcpdev.board.dao.GalleryMapper;
import com.jcpdev.board.model.Gallery;

@Service
public class GalleryService {
	
	private final GalleryMapper dao;
	
	public GalleryService(GalleryMapper dao) {
		this.dao=dao;
	}
	
	public List<Gallery> getAll(){
		return dao.getAll();
	}

	// 파일 저장
	public int save(Gallery vo) throws IllegalStateException, IOException {
	      List<MultipartFile> files = vo.getFiles();
	      StringBuilder sb = new StringBuilder();
	      // 저장위치
	      String path = "d:\\desktop\\upload";

	      if (files != null && files.size() > 0) { //첨부된 파일이 있을 때
	            for (MultipartFile f : files) {	//첨부된 파일리스트에 하나씩
	               
	               String newpath="";
	               String fileName = "board_" + randomString(f.getOriginalFilename());   //board_원래 파일명.확장자 ,파일명 변경하여 db에 저장
	               if (!fileName.equals("")) {
	                  newpath = path + "\\" + fileName;   //업로드경로+파일명
	                  sb.append(fileName).append(",");
	                  // 선택한 파일을 서버로 전송
	                  File upfile = new File(newpath); //newpath에 java.io.File 객체를 생성하고
	                  f.transferTo(upfile);				//upload 폴더로 전송 transferTo : apche 파일 업로드 라이브러리 메소드
	               }
	            }
	            vo.setFilename(sb.toString());		//파일명 저장 컬럼 1개, 저장할 파일명을 ,로 구분하여 하나의 문자열 생성
	         }
	      return dao.insert(vo);
	   }
	
	
	// 파일 이름 변환
	public String randomString(String oldfile) {
	      int leftLimit = 48; // 숫자 '0'
	      int rightLimit = 122; // 알파벳 'z'
	      int targetStringLength = 10;
	      Random random = new Random();
	      String ext = oldfile.substring(oldfile.indexOf("."), oldfile.length());
	      String rString = random.ints(leftLimit,rightLimit + 1)
	        .filter(i -> (i <= 57 || (i >= 65 && i <= 90) || i >= 97))
	        .limit(targetStringLength)
	        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	        .toString();
	      
	      //또는 UUID.randomUUID().toString();
	      return rString+ext;
	   }
	
	
}
