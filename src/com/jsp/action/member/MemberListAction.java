package com.jsp.action.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.Criteria;
import com.jsp.command.SearchCriteria;
import com.jsp.service.MemberService;

public class MemberListAction implements Action {

	private MemberService searchMemberService;
	public void setSearchMemberService(MemberService searchMemberService) {
		this.searchMemberService = searchMemberService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "/member/list";
		
		String pageParam = request.getParameter("page");
		String perPageNumParam = request.getParameter("perPageNum");
		String keyword = request.getParameter("keyword");
		String searchType = request.getParameter("searchType");
		
		SearchCriteria cri = new SearchCriteria();
		cri.setKeyword(keyword);
		cri.setSearchType(searchType);
		
		boolean criFlag = true;
		criFlag = criFlag && pageParam != null
						  && !pageParam.isEmpty()
						  && perPageNumParam != null
						  && !perPageNumParam.isEmpty();
		
		if(criFlag) {
			try {
				cri.setPage(Integer.parseInt(pageParam));
				cri.setPerPageNum(Integer.parseInt(perPageNumParam));			
			} catch(Exception e) {
				response.sendError(response.SC_BAD_REQUEST);
				return null;
			}
		}
		
		try {
			
			Map<String,Object> dataMap = searchMemberService.getMemberListForPage(cri);
			request.setAttribute("dataMap", dataMap);
			
		} catch (Exception e) {
			url = "/error/500";
		}
		
		return url;
	}

}
