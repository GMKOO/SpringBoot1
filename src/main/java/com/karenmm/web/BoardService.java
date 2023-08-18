package com.karenmm.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	@Autowired
	private Util util;

	@Autowired
	private BoardDAO boardDAO;

	public List<BoardDTO> boardList() {
		return boardDAO.boardList();
	}

	public BoardDTO datail(int bno) {
		boardDAO.readUP(bno);
		// 읽음수 1더하기
		return boardDAO.detail(bno);
	}

	public int write(BoardDTO dto) {
		// ip
		dto.setBip(util.getIp());
		// uuid
		dto.setUuid(UUID.randomUUID().toString());
		return boardDAO.write(dto);

	}

}
