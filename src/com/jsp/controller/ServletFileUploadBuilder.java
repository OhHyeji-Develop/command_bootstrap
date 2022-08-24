package com.jsp.controller;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class ServletFileUploadBuilder {
	
	public static ServletFileUpload  build(int MEMORY_THRESHOLD, int MAX_FILE_SIZE, int MAX_REQUEST_SIZE) {
	
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(MEMORY_THRESHOLD); // 바가지 사이즈 설정
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		upload.setFileSizeMax(MAX_FILE_SIZE); // 파일의 크기가 얼만지
		
		upload.setSizeMax(MAX_REQUEST_SIZE); // 파일의 갯수가 몇갠지
		
		return upload;
	}
}
