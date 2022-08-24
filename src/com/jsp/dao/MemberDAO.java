package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

public interface MemberDAO {
	
	//회원리스트 조회
	List<MemberVO> selectMemberList(SqlSession session)throws Exception; 
	List<MemberVO> selectMemberList(SqlSession session,Criteria cri)throws Exception;
	
	
	// 일반 리스트 전체 개수
	int selectMemberListCount(SqlSession session) throws Exception;
	
	
	// 회원정보 조회
	MemberVO selectMemberById(SqlSession session,String id) throws SQLException;
	
	// 회원 추가
	public void insertMember(SqlSession session, MemberVO member) throws SQLException;
	
	// 회원 수정
	public void updateMember(SqlSession session, MemberVO member) throws SQLException;
	
	// 회원정보 삭제
	void deleteMember(SqlSession session,String id) throws SQLException;
	

	// 회원 활성화
	void enabledMember(SqlSession session,String id, int enabled) throws SQLException;	
}
