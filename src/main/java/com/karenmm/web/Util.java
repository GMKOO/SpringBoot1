package com.karenmm.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// Controller Service Repository Component = 객체 

@Component
public class Util {

	// 문자열이 들어오면 숫자로 변경하기

	public int strToInt(String str) {

		/*int result =0;
		 *  try { 
		 * 			result = Integer.parseInt(str);
		 * }	catch (Exception e) {
		 * 	String re =""; //숫자인것만 모을 스트링 입니다.
		 * 
		 * for (int i=0; i<str.length(); i++) {
		 * 	if(Character.isDigit(str.charAt(i))) { // 숫자인지 물어봄
		 * 		re += str.charAt(i);
		 * }
		 * } result = Integer.parseInt(re);
		 * }
		 * return result;
		 * 
		 * 
		 * 
		 * 2안
		 * for(int i=0; i< str.length(); i++) { 
		 * if (Character.isDigit(str.charAt(i)) {
				sb.append(str.charAt(i));
		 * 
		 * 
		 * 
		 */
		
		int result = 0;
		
		StringBuilder sb = new StringBuilder();
		
		try {
			
			result=Integer.parseInt(str);
		}catch (Exception e) {
		
		for (char c : str.toCharArray()) {
			if (Character.isDigit(c)) {
				sb.append(c);
			}

		}	return result= Integer.parseInt(sb.toString());
		}
	
		return result;
	}

	public String exchange(String str) {

//특수문자 막는 기능
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");

		return str;

	}

	public String getIp() {
		HttpServletRequest request = ((ServletRequestAttributes) // 형변환
		RequestContextHolder.currentRequestAttributes()).getRequest();

		String ip = null; //
		ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;

	}

	public boolean isNum(Object obj) {
		try {
			Integer.parseInt(String.valueOf(obj));
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
		
	

	}
	
	public int obtoint(Object obj) {
		
	int	result=Integer.parseInt(String.valueOf(obj));
		
		return result;
		
	}
	
}
