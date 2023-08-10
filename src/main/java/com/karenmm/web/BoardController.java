package com.karenmm.web;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/board")
	public String board(Model model) {
		
	

		List<BoardDTO> list = boardService.boardList();
		
		model.addAttribute("list", list);
	//	BoardDTO dto = new BoardDTO();
			//System.out.println(list);
		
		return "board";
	}
	
	@ResponseBody
	@PostMapping("/detail")
	public String detail(@RequestParam("bno") int bno) {
		// System.out.println(bno);
		String content	= boardService.datail(bno);
		JSONObject json = new JSONObject();
		json.put("content", content);
		return json.toString();
		
		//return	"{content : \"본문내용입니다\"}";
	}
	
	@GetMapping("/write")
	public String write() {
		
	


		
		return "write";
	}
	
	@GetMapping("/naver")
	public String naver() {
		
	


		
		return "naver";
	}
	
	@GetMapping("/naver2")
	public String naver2() {
		
	


		
		return "naver2";
	}
	
	
}
