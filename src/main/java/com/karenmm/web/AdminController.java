package com.karenmm.web;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

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
		
		@GetMapping(value= {"/main", "/home"})
		public String adminmain() {
			
			return "admin/main";
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
		@GetMapping("/board")
		public String adminboard() {
			
			return "admin/board";
		}
		
		@GetMapping("/main1")
		public String adminmain1() {
			
			return "admin/main1";
		}
		
		@GetMapping("/multiboard")
		public String adminmultboard() {
			
			return "admin/multiboard";
		}
		
		@GetMapping("/notice")
		public String notice(Model model) {
			
			//List<Map<String,Object>> list = adminService.list();
			
			//1.데이터베이스까지 연결하기
			//2.데이터불러오기
			//3.데이터 jsp로 보내기
			//model.addAttribute("list", list);
			
			return "admin/notice";
		}
		
		@PostMapping("/noticeWrite")
		public String noticceWrite(@RequestParam("upFile") MultipartFile upfile,@RequestParam Map<String,Object> map) {
			System.out.println(map);
			
			
			if(!upfile.isEmpty()) {
			
				//저장할 경로명 뽑기 request뽑기
		         HttpServletRequest request = 
		                 ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String path = request.getServletContext().getRealPath("/upload");
		         
			File newFileName = new File(upfile.getOriginalFilename());

			
			System.out.println("실제경로"+path);
			map.put("mno",4);
			//up파일 정보보기
			System.out.println(upfile.getOriginalFilename());
			System.out.println(upfile.getSize());
			System.out.println(upfile.getContentType());
			//adminService.noticeWrite(map);
			
			}
			return "redirect:/admin/notice";
			
		
		}
		
		
}
