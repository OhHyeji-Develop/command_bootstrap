package com.jsp.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO{

	@Override
	public List<MemberVO> selectMemberList(SqlSession session) throws Exception {
		List<MemberVO> memberList=null;
		try {
		  
			memberList= session.selectList("Member-Mapper.selectMemberList");
		}catch(Exception e) {
			//에러처리
			throw e;
		}
		return memberList;
	}

	@Override
	public List<MemberVO> selectMemberList(SqlSession session, Criteria cri) throws Exception {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<MemberVO> memberList 
		= session.selectList("Member-Mapper.selectMemberList",null,rowBounds);
		
		return memberList;
	}
	
	
	@Override
	public int selectMemberListCount(SqlSession session) throws Exception {
		int totalCount 
		= session.selectOne("Member-Mapper.selectMemberListCount");
		
		return totalCount;
	}

	@Override
	public MemberVO selectMemberById(SqlSession session, String id) throws SQLException {
		MemberVO member=session.selectOne("Member-Mapper.selectMemberById",id);			
		return member;
	}

	@Override
	public void insertMember(SqlSession session, MemberVO member) throws SQLException {
		session.update("Member-Mapper.insertMember",member);
		
	}
	
	@Override
	public void updateMember(SqlSession session, MemberVO member) throws SQLException {
		session.update("Member-Mapper.updateMember",member);

	}

	@Override
	public void deleteMember(SqlSession session, String id) throws SQLException {
		session.update("Member-Mapper.deleteMember",id);		
	}

	@Override
	public void enabledMember(SqlSession session, String id, int enabled) throws SQLException {
		
		Map<String, Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("id", id);
		dataMap.put("enabled",enabled);
		
		session.update("Member-Mapper.enabledMember",dataMap);
		
	}

}






