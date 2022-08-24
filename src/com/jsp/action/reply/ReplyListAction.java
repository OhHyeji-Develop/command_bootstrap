package com.jsp.action.reply;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.jsp.action.Action;
import com.jsp.command.SearchCriteria;
import com.jsp.command.SearchCriteriaCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.controller.JSONResolver;
import com.jsp.service.ReplyService;

public class ReplyListAction implements Action {
	
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url=null;
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		

		SearchCriteriaCommand criCMD = 
			 HttpRequestParameterAdapter.execute(request,SearchCriteriaCommand.class);
		SearchCriteria cri = criCMD.toSearchCriteria();
		
		Map<String, Object> dataMap = replyService.getReplyList(bno, cri);
		
		JSONResolver.view(response, dataMap);
		
		return url;
	}

}







