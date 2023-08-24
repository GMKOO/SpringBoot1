package com.karenmm.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MultiBoardController {

	@Autowired
	private MultiBoardService mbService;
	
	@GetMapping("/multiboard")
	public String multiboard(@RequestParam(value = "board", defaultValue = "1" , required = false) int board,Model model) {
		
		//화면에 보여줄 게시판 목록도 가져오겠습니다.
		List<Map<String,Object>> list = mbService.list(board);
		List<Map<String,Object>> boardlist = mbService.boardlist();
		
		model.addAttribute("list", list);
		model.addAttribute("boardlist", boardlist);
		
		System.out.println("mb게시판"+list);
		
		
		return "multiboard";
	}
	
	@GetMapping("/mbwrite")
	public String mbWrite(@RequestParam(value = "board", defaultValue = "1" , required = false) int board,Model model,HttpSession session) {
		
	
		
		
		if(session.getAttribute("mid") != null) {
			model.addAttribute("board", board);
			
			System.out.println("board내용"+board);
			return "mbwrite";
			
		}else { 
			
			return "redirect:/login?error=login";
			
		}

	}
	
	@PostMapping("/mbwrite")
	public String mbWrite(@RequestParam Map<String,Object> map,HttpSession session) {
		
		
		if(session.getAttribute("mid") != null) {
			
			map.put("mid",session.getAttribute("mid"));
			
			 mbService.mbWrite(map);  // 이번에는 selectKey라는 기법입니다
			System.out.println(map.get("mb_no"));
			System.out.println("mbwritepost"+map);
		
			
			 
			
			return "redirect:/mbdetail?board="+map.get("board")+"&mbno="+map.get("mb_no");
		
			
		}else { 
			
			return "redirect:/login?error=login";
			
		}

	}
	
	@GetMapping("/mbdetail")
	public String mbDetail(@RequestParam(value ="mbno",required = true) int mbno,Model model) {
		
		System.out.println(mbno);
		Map<String,Object> detail = mbService.mbDetail(mbno);
		model.addAttribute("detail", detail);
		return "mbdetail";
	}
	
		
		
	
}
