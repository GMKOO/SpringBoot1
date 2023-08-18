package com.karenmm.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;
	


	public List<Map<String, Object>> list() {
	
		return noticeDAO.list();
	}

}
