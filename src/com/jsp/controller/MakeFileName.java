package com.jsp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jsp.dto.AttachVO;

public class MakeFileName {
	public static String toUUIDFileName(String fileName, String delimiter) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid + delimiter + fileName;
	}
	
	public static String parseFileNameFromUUID(String fileName, String delimiter) {
		String[] uuidFileName = fileName.split(delimiter);
		
		return uuidFileName[uuidFileName.length - 1];
	}
	
	public static List<AttachVO> parseFileNameFromAttaches(List<AttachVO> attachList, String delimiter){
		List<AttachVO> renameAttachList = new ArrayList<AttachVO>();
		
		if(attachList != null) {
			for(AttachVO attach : attachList) {
				
				// DB상의 fileName
				String fileName = attach.getFileName();
				
				// UUID 가져오기
				fileName = parseFileNameFromUUID(fileName, delimiter);
				// filename
				attach.setFileName(fileName);
				renameAttachList.add(attach);
			}
		}
		return renameAttachList;
	}
}
