package com.jsp.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIdException;

public class LoginSearchMemberServiceImpl extends SearchMemberServiceImpl 
									implements LoginSearchMemberService {

	private SqlSessionFactory sqlSessionFactory;	
	public void setSqlSessionFactoryForLogin(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private MemberDAO memberDAO;
	public void setSearchMemberDAO(MemberDAO memberDAO) {		
		this.memberDAO = memberDAO;
	}

	@Override
	public void login(String id, String pwd) throws NotFoundIdException, InvalidPasswordException, SQLException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			MemberVO member = memberDAO.selectMemberById(session, id);
			if (member == null)
				throw new NotFoundIdException();
			if (!pwd.equals(member.getPwd()))
				throw new InvalidPasswordException();
			
		} finally {
			session.close();
		}

	}

}
