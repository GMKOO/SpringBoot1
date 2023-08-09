package com.karenmm.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.karenmm.web.BoardDTO;
import com.karenmm.web.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	@GetMapping("/board")
	public String board(Model model) {
		
	

		List<BoardDTO> list = boardService.boardList();
		
		model.addAttribute("list", list);
	//	BoardDTO dto = new BoardDTO();
			System.out.println(list);
		
		return "board";
	}
	
}
