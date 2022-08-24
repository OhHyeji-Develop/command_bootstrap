package com.jsp.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.XSSHttpRequestParameterAdapter;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

public class BoardRegistAction implements Action{
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String url="/board/regist_success";
		
		try {
		BoardVO board 
		= (BoardVO)XSSHttpRequestParameterAdapter.execute(request, BoardVO.class,true);
		
		board.setContent(request.getParameter("content"));
		
		boardService.regist(board);
		}catch(Exception e) {
			e.printStackTrace();
			//url=null;
			throw e;
		}		
		
		
		return url;
	}

}





