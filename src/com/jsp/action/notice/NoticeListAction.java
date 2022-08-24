package com.jsp.action.notice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.SearchCriteria;
import com.jsp.command.SearchCriteriaCommand;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.service.MemberService;
import com.jsp.service.NoticeService;

public class NoticeListAction implements Action {
   
   private NoticeService noticeService;
   public void setNoticeService(NoticeService noticeService) {
      this.noticeService = noticeService;
   }

   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String url="/notice/list";
      
      SearchCriteria cri=null;
      //처리
      try {
         SearchCriteriaCommand command = HttpRequestParameterAdapter.execute(request,SearchCriteriaCommand.class );
         cri = command.toSearchCriteria();
         
         Map<String,Object> dataMap = noticeService.getNoticeList(cri);
         request.setAttribute("dataMap", dataMap);
      }catch(Exception e) {
         e.printStackTrace();
         //response.sendError(response.SC_INTERNAL_SERVER_ERROR);
         //url=null;
         throw e;
      }    
      
      //출력
    
      return url;
   }

}