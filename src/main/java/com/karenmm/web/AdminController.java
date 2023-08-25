package com.karenmm.web;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
		
	
		
		@GetMapping("/notice")
		public String notice(Model model) {
			
			List<Map<String,Object>> list = adminService.list();
			
			//1.데이터베이스까지 연결하기
			//2.데이터불러오기
			//3.데이터 jsp로 보내기
			model.addAttribute("list", list);
			
			return "admin/notice";
		}
		
		@PostMapping("/noticeWrite")
		public String noticeWrite(@RequestParam("upFile") MultipartFile upfile,@RequestParam Map<String,Object> map) {
			System.out.println(map);
			
			
			//2023-08-22 요구사항확인
			//
			
			if(!upfile.isEmpty()) {
			
				//저장할 경로명 뽑기 request뽑기
		         HttpServletRequest request = 
		                 ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		         // String타입의 경로를 file형태로 바꿔주겠습니다.
		         String path = request.getServletContext().getRealPath("/upload");
		         
		        // File filePath = new File(path);
		         
			//진짜로 파일 업로드하기 경로 + 저장할 파일명
		    //중복이 발생할 수 있기 때문에... 파일명 +날짜+ID.파일확장자 
		         //						uuid +파일명 + .확장자
		         //                    아이디 + 날짜 +파일명 . 확장자 
		         UUID uuid = UUID.randomUUID();
		
		
		//날짜 뽑기 SimpleDateFormat
		//Date date = new Date();
		LocalDateTime ldt = LocalDateTime.now();
		String format = ldt.format(DateTimeFormatter.ofPattern("YYYYMMddHHmmss"));
		//SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
		//String dataTime = sdf.format(date);
		//String realFileName = dataTime.toString()+uuid.toString() + upfile.getOriginalFilename();
		//String realFileName = dataTime.toString() + upfile.getOriginalFilename();
		//String realFileName = format.toString() + upfile.getOriginalFilename();
		//날짜 +uuid +실제 파일명으로 사용하겠습니다.
		//String realFileName = format.toString() +uuid.toString()+ upfile.getOriginalFilename();
		String realFileName = format +uuid.toString()+ upfile.getOriginalFilename();
		
			//File newFileName = new File(path+"/"+upfile.getOriginalFilename());
			//File newFileName = new File(path,upfile.getOriginalFilename());
		File newFileName = new File(path,realFileName);
			//이제 파일 올립니다.
			try {
				upfile.transferTo(newFileName);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("저장 끝.");
			/* transferto를 사용하거나 FileCopyUtils를 사용하면된다 
			//FileCopyUtils를 사용하기 위해서는 오리지널 파일을 byte[]로 만들어야 합니다.
			try {
				FileCopyUtils.copy(upfile.getBytes(), newFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			*/
			System.out.println("실제경로"+path);
			map.put("mno",4);
			//up파일 정보보기
			System.out.println(upfile.getOriginalFilename());
			System.out.println(upfile.getSize());
			System.out.println(upfile.getContentType());
		
			map.put("upFile", upfile.getOriginalFilename());
			map.put("realFile", realFileName);
			adminService.noticeWrite(map);
			System.out.println(realFileName);
			System.out.println(upfile.getOriginalFilename());
			}
			return "redirect:/admin/notice";
			
		
		}
		
		@GetMapping("/mail")
		public String mail() {
			return "admin/mail";
			
		}
		
		@PostMapping("/mail")
		public String mail(@RequestParam Map<String,Object> map) throws EmailException {
			
			
			//System.out.println(map);
			// return "forward:/mail";
			//util.simpleMailSender(map);
		util.HtmlMailSender(map);
			
			return "admin/mail";
		}
		
		@ResponseBody
		@PostMapping("/noticeDetail")
		public String noticeDetail(@RequestParam("nno") int nno,Model model) {
			
			JSONObject	json = new JSONObject();
		String result =adminService.noticeDetail(nno);
			
		json.put("content", result);
			
			/*
			System.out.println();
			
			ObjectNode	json = JsonNodeFactory.instance.objectNode();
			Map<String, Object> maaaap = new HashMap<String, Object>();
			maaaap.put("bno",123);
			maaaap.put("btitle",1234);
			
			ObjectMapper jsonMap = new ObjectMapper();
			
			try {
				json.put("map", jsonMap.writeValueAsString(maaaap));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			json.put("content", adminService.noticeDetail(nno));
			
	*/
	
			return json.toString();
		}
		
		//noticeHide
		@ResponseBody
		@PostMapping("/noticeHide")
		public String noticeHide(@RequestParam("nno") int nno) {
			
			JSONObject	json = new JSONObject();
		//String result = adminService.noticeHide(nno);
		
		//ObjectNode json1 = JsonNodeFactory.instance.objectNode();
		int result = adminService.noticeHide(nno);
		
	
		json.put("result", result);
		
		System.out.println(result);
		System.out.println(json.toString());
			
			return json.toString();
		}

		@RequestMapping(value="/multiboard", method = RequestMethod.GET)
		public String multiBoard(Model model) {

			List<Map<String, Object>> map = adminService.setupBoardList();
			
			model.addAttribute("view", map);
			
			return "admin/multiboard";
		}
		
		@PostMapping("/member")
		public String member(Model model) {
			
			List<Map<String,Object>> list=adminService.member();
			
			
			
			return "";
		}
		
		@RequestMapping(value="/member", method = RequestMethod.GET)
		public ModelAndView member() {
			ModelAndView mv = new ModelAndView("admin/member");
			mv.addObject("memberList", adminService.memberlist());
			System.out.println(mv);
			return mv;
		}
		
		@PostMapping("/multiboard")
		public String multiBoard1(@RequestParam Map<String,Object> map , Model model ) {
			
			

			int result = adminService.multiboard1(map);
			
			model.addAttribute("create", result);
			
			return "redirect:/admin/multiboard";
		}
	
			@RequestMapping(value="/gradeChange" ,method = RequestMethod.GET )
			public String gradeChange(@RequestParam Map<String,String> map) {
				int result =adminService.gradeChange(map);
				
				
				return "redirect:/admin/member";
				
			}
			
			
			
		
}
