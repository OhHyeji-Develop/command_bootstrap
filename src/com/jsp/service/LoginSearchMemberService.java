package com.jsp.service;

import java.sql.SQLException;

import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIdException;

public interface LoginSearchMemberService extends MemberService {
   
   void login(String id, String pwd) throws NotFoundIdException, InvalidPasswordException, SQLException;

}