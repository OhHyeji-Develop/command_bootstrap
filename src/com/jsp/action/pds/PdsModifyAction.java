package com.jsp.action.pds;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.jsp.action.Action;
import com.jsp.controller.FileUploadResolver;
import com.jsp.controller.GetUploadPath;
import com.jsp.controller.XSSMultipartHttpServletRequestParser;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class PdsModifyAction implements Action {

	private PdsService pdsService;

	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/pds/modify_success";
		
		try {
			// 파일 삭제, 저장
			PdsVO pds = modifyAttaches(request, response);
			
			
			// DB 수정
			pdsService.modify(pds);
			
			request.setAttribute("pds", pds);
		} catch (Exception e) {
			e.printStackTrace();
			url = null;
			throw e;
		}
		
		return url;
	}

	// 업로드 파일 환경 설정
	final private int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	final private int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	final private int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB
	
	private PdsVO modifyAttaches(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PdsVO pds = null;
		
		XSSMultipartHttpServletRequestParser multi = new XSSMultipartHttpServletRequestParser(request, 
																	MEMORY_THRESHOLD, 
																	MAX_FILE_SIZE,
																	MAX_REQUEST_SIZE);
		// 파일 삭제 및 DB 삭제
		String[] deleteFiiles = multi.getParameterValues("deleteFile");
		if(deleteFiiles != null && deleteFiiles.length > 0) {
			// 최소 1개 이상이니까 for문을 돌림
			for(String anoStr : deleteFiiles) {
				int ano = Integer.parseInt(anoStr);
				AttachVO attach = pdsService.getAttachByAno(ano);
				String filePath = attach.getUploadPath() + File.separator + attach.getFileName();
				File targetFile = new File(filePath);
				
				if(targetFile.exists()) {
					targetFile.delete();	// 파일 삭제
				}
				pdsService.removeAttachByAno(ano); // DB삭제
			}
		}
		
		// 추가된 파일 저장
		FileItem[] fileItems = multi.getFileItems("uploadFile");
		// pds만들때 사용하기때문에 전역변수로 생성
		List<AttachVO> attachList = null;
		
		if(fileItems != null && fileItems.length > 0) {
			// 저장경로 가져오기
			String uploadPath = GetUploadPath.getUploadPath("pds.upload");
			// 파일명
			List<File> fileList = FileUploadResolver.fileUpload(fileItems, uploadPath);
			
			attachList = new ArrayList<AttachVO>();
			
			// 첨부파일이 없을 경우...
			if(fileList.size() > 0)for(File file:fileList) {
				AttachVO attach = new AttachVO();
				attach.setFileName(file.getName());
				attach.setUploadPath(uploadPath);
				attach.setFileType(file.getName().substring(file.getName().lastIndexOf(".")+1));
				attachList.add(attach);
			}
		}
			
		pds = new PdsVO();
		pds.setPno(Integer.parseInt(multi.getParameter("pno")));
		pds.setTitle(multi.getXSSParameter("title"));
		pds.setWriter(multi.getParameter("writer"));
		pds.setContent(multi.getParameter("content"));
		pds.setAttachList(attachList);
		
		return pds;
	}

}
