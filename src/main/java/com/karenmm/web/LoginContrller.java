package com.karenmm.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginContrller {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if ( session.getAttribute("mid") != null) {
			
			session.removeAttribute("mid");
		}
		if ( session.getAttribute("mname") != null) {
			session.removeAttribute("mname");
			
		}
		
		session.invalidate();
		return "redirect:/";
	}
	
	//2023-08-11
	@GetMapping("/login")
	public String index() {
		return "login";
	}
	
	//2023-0816 프레임워크 프로그래밍 
	@PostMapping("/login")   //#requestParam MAP 사용시 입력 변수를 무한대로 사용가능# 
	public String login(@RequestParam Map<String,String> map,HttpSession session) {  
		System.out.println(map);
		
	
		Map<String, Object> result = loginService.login(map);
		
		
		//int count =Integer.parseInt(String.valueOf(result.get("count")));

		System.out.println(result);
		
		
		
		if(String.valueOf(result.get("count")).equals("1")) { 
			
			session.setAttribute("mid", map.get("id"));
			session.setAttribute("mname", result.get("m_name"));
			
			System.out.println(session.getAttribute("mname"));
			System.out.println(session.getAttribute("mid"));
			
			
		return "redirect:/";
		
		} else {
			return "login";
		}
	}
	
	//2023-08-18 요구사항 확인 
	//PathVariable 경로변수
	@GetMapping("/myInfo@{id}")
	public ModelAndView myInfo(@PathVariable("id") String id,HttpSession session) {
		
		
		Map<String,Object> myInfo = loginService.myInfo(id); 
		ModelAndView mv = new ModelAndView("myInfo"); // 이동할 jsp파일명
		mv.addObject("my", myInfo); //값 붙이기
		
		System.out.println("jsp가 보내준 값 : " + id);
		System.out.println(id.equals(session.getAttribute("mid")));
		
		//if(session.getAttribute("mid")!=null) {
			
			//return "myInfo";
		//}
		return mv;
	}

}
