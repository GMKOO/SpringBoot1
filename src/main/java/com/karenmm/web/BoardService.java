package com.karenmm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;

	public List<BoardDTO> boardList() {
		
		return boardDAO.boardList();
	}

	public String datail(int bno) {
		return boardDAO.detail(bno);
	}
	
	


}
