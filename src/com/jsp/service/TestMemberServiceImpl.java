package com.jsp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.jsp.dto.MemberVO;

public class TestMemberServiceImpl {
	private MemberService service = new MemberServiceImpl();
	
	@Test
	public void testGetMemberList()throws Exception{
		List<MemberVO> memberList = service.getMemberList();
		
		Assert.assertEquals(7, memberList.size());
	}
}
