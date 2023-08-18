package com.karenmm.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private Util util;

	@Autowired
	private AdminService adminService;
	
		@GetMapping(value= {"/", "/admin"})
		public String adminIndex() {
			
			return "admin/index";
		}
		
		@PostMapping("/login")
		public String adminLogin(@RequestParam Map<String,Object> map,HttpSession session) {
			
			System.out.println(map);
			
			Map<String, Object> result =adminService.adminLogin(map);
			
			System.out.println(result);
			System.out.println(result.get("count"));
			
			
			//if( Integer.parseInt(String.valueOf(map.get("m_grade"))) < 5) {
				if(util.obtoint(result.get("count")) ==1 && (util.obtoint(result.get("m_grade"))>5)) {
					
					session.setAttribute("mid",map.get("id"));
					session.setAttribute("mname",map.get("m_name"));
					session.setAttribute("mgrade",map.get("m_grade"));
					
					return "redirect:/admin/main";
				} else {
					return "redirect:/admin/admin?error=login";
				}
		}
		
}
