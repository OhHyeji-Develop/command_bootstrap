package com.jsp.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;

public class SearchMemberDAOImpl extends MemberDAOImpl implements SearchMemberDAO {

	@Override
	public List<MemberVO> selectSearchMemberList(SqlSession session, SearchCriteria cri) throws Exception {
		int offset = cri.getStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		List<MemberVO> memberList 
		= session.selectList("Member-Mapper.selectSearchMemberList",cri,rowBounds);
		
		return memberList;
	}

	@Override
	public int selectSearchMemberListCount(SqlSession session, SearchCriteria cri) throws Exception {
		int totalCount 
		= session.selectOne("Member-Mapper.selectSearchMemberListCount",cri);
		
		return totalCount;
	}


}
