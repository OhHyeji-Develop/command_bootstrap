package com.jsp.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestParameterAdapter2 {
	
	public static <T>T execute(MultipartHttpServletRequestParser multi, Class<T> className) throws Exception{
		//의존성 확인 및 조립
		Method[] methods = className.getMethods(); //클래스의 모든 메서드 추출
		
		//인스턴스 생성
		T obj = className.newInstance(); //클래스를 싱글톤으로 인스턴스화
		
		//setter확인
		for(Method method : methods) {
			if(method.getName().indexOf("set") == 0) { //세터이면
				String requestParamName = method.getName().replace("set", ""); //set을 띄고
				requestParamName = (requestParamName.charAt(0) + "").toLowerCase() + requestParamName.substring(1); //첫글자를 소문자로 바꾸면 파라미터 이름이됨
				
				String[] paramValues = multi.getParameterValues(requestParamName); //파라미터 이름으로 모든 파라미터의 벨류 추출 -> 스트링 배열로
				
				if(paramValues != null && paramValues.length > 0) { //배열의 값이 존재하면
					if(method.getParameterTypes()[0].isArray()) { //꺼낸 파라미터의 타입이 배열이면(ex.체크박스로 여러개 선택한 경우)
						method.invoke(obj, new Object[] {paramValues }); //obj에 배열로 만들어서 넣고
					} else { //배열이 아니면
						method.invoke(obj, paramValues[0]); //값을 넣는다.
					}
				}
			}
		}
		
		return obj;
	}
}
