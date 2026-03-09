package com.spring.token.config.jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Cookie 생성 및 추출 유틸리티
 */
public class CookieUtil {

    /**
     * httpOnly Cookie 생성
     * @param name     쿠키 이름
     * @param value    쿠키 값 (JWT 토큰)
     * @param maxAge   만료 시간 (초)
     * @param httpOnly JS 접근 차단 여부
     */
	
	//7.쿠키생성
    public static Cookie createCookie(String name, String value, int maxAge, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/"); 		//어떤 경로에서 사용할것인가?
        cookie.setMaxAge(maxAge);
        
    	return cookie;
    }

    /**
     * Request에서 특정 이름의 Cookie 값 추출
     */
    //9.
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        
        for(Cookie cookie : cookies) {
        	if(name.equals(cookie.getName())) {
        		return cookie.getValue();
        	}
        }
    	return null;
    }

    /**
     * Cookie 삭제 (maxAge=0으로 덮어씌우기)
     */
    //11.로그아웃
    public static void deleteCookie(HttpServletResponse response, String name) {
    
    	Cookie cookie = new Cookie(name, null);
    	cookie.setMaxAge(0);
    	cookie.setPath("/");
    	response.addCookie(cookie);
    }
}