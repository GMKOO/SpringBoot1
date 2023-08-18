package com.karenmm.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiBoardService {
	
	@Autowired
	private MultiBoardDAO mbDAO;

	public List<Map<String, Object>> list(int board) {
		return mbDAO.list(board);
	}

	public List<Map<String, Object>> write(int board) {
		return mbDAO.write(board);
	}

	public int mbWrite(Map<String, Object> map) {
		
		return mbDAO.mbWrite(map);
	}



	public Map<String, Object> mbDetail(int mbno) {
		// TODO Auto-generated method stub
		return mbDAO.mbDetail(mbno);
	}

}
