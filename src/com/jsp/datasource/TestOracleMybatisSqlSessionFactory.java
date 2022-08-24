package com.jsp.datasource;

import org.junit.Before;
import org.junit.Test;

import com.jsp.dto.MemberVO;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;

public class TestOracleMybatisSqlSessionFactory {

	private OracleMybatisSqlSessionFactory sqlSessionFactroy = new OracleMybatisSqlSessionFactory();

	private SqlSession session;

	@Before
	public void init() throws Exception {
		session = sqlSessionFactroy.openSession();

	}

	@Test
	public void testSqlSessionFactory() throws Exception {
		Assert.assertNotNull(sqlSessionFactroy);
	}

	@Test
	public void testsqlSession() throws Exception {
		Collection<String> mapNames = (Collection<String>) session.getConfiguration().getMappedStatementNames();
		Assert.assertNotNull(mapNames.contains("Member-Mapper.selectMemberList"));
	}

	@Test
	public void testMemberList() throws SQLException {
		List<MemberVO> memberList = session.selectList("Member-Mapper.selectMemberList");

		Assert.assertEquals(7, memberList.size());
	}

	@After
	public void close() throws Exception {
		if (session != null)
			session.close();
	}
}