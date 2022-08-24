package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.PdsVO;

public class PdsDAOImpl implements PdsDAO{
	

	@Override
	public List<PdsVO> selectPdsCriteria(SqlSession session,Criteria cri) throws SQLException {
		
		SearchCriteria searchCri = (SearchCriteria)cri;
		
		int offset=cri.getStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<PdsVO> pdsList=
		   session.selectList("Pds-Mapper.selectSearchPdsList",searchCri,rowBounds);	
			
		return pdsList;
	}

	@Override
	public int selectPdsCriteriaTotalCount(SqlSession session,Criteria cri) throws SQLException {
		SearchCriteria searchCri = (SearchCriteria)cri;
		int count=session.selectOne("Pds-Mapper.selectSearchPdsListCount",searchCri);
		return count;
	}

	@Override
	public PdsVO selectPdsByPno(SqlSession session,int pno) throws SQLException {
		PdsVO pds=session.selectOne("Pds-Mapper.selectPdsByPno",pno);
		return pds;
	}

	@Override
	public void insertPds(SqlSession session,PdsVO pds) throws SQLException {
		session.update("Pds-Mapper.insertPds",pds);
	}

	@Override
	public void updatePds(SqlSession session,PdsVO pds) throws SQLException {
		session.update("Pds-Mapper.updatePds",pds);
	}

	@Override
	public void deletePds(SqlSession session,int pno) throws SQLException {
		session.update("Pds-Mapper.deletePds",pno);
	}

	@Override
	public void increaseViewCnt(SqlSession session,int pno) throws SQLException {
		session.update("Pds-Mapper.increaseViewCnt",pno);
	}

	@Override
	public int getSeqNextValue(SqlSession session) throws SQLException {
		int pno=session.selectOne("Pds-Mapper.selectPdsSeqNext");
		return pno;
	}
}
