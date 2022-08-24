package com.jsp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.datasource.OracleMybatisSqlSessionFactory;
import com.jsp.dto.MemberVO;

public class MemberServiceImpl implements MemberService{
	
	private SqlSessionFactory sqlSessionFactory;
	private MemberDAO memberDAO;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List<MemberVO> getMemberList() throws Exception {
		SqlSession session= sqlSessionFactory.openSession(false);
		List<MemberVO> memberList = null;
		
		try {
			memberList = memberDAO.selectMemberList(session);
			
			session.commit();
			
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null) session.close();
		}
		return memberList;
	}
	
	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws Exception {
		SqlSession session= sqlSessionFactory.openSession(false);
		List<MemberVO> memberList = null;
		
		try {
			memberList = memberDAO.selectMemberList(session,cri);
			
			session.commit();
			
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null) session.close();
		}
		return memberList;
	}

	@Override
	public Map<String, Object> getMemberListForPage(Criteria cri) throws Exception {
		SqlSession session= sqlSessionFactory.openSession(false);
		Map<String,Object> dataMap =null;
		
		try {
			
			//처리.......
			List<MemberVO> memberList 
				= memberDAO.selectMemberList(session, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(memberDAO.selectMemberListCount(session));
			
			dataMap = new HashMap<String,Object>();
			dataMap.put("memberList", memberList);
			dataMap.put("pageMaker",pageMaker);
			
			session.commit();
			
		}catch(Exception e) {
			session.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			if(session!=null) session.close();
		}
		return dataMap;
	}

	@Override
	public MemberVO getMember(String id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {			
			MemberVO member = memberDAO.selectMemberById(session, id);
			return member;
		} finally {
			session.close();
		}
	}

	@Override
	public void regist(MemberVO member) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {			
			memberDAO.insertMember(session, member);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void modify(MemberVO member) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {			
			memberDAO.updateMember(session, member);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void remove(String id) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {			
			memberDAO.deleteMember(session, id);
		} finally {
			session.close();
		}
		
	}

	@Override
	public void enabled(String id, int enabled) throws Exception {
		SqlSession session = sqlSessionFactory.openSession();
		
		try {			
			memberDAO.enabledMember(session, id, enabled);
		} finally {
			session.close();
		}
		
	}
	
	
}






