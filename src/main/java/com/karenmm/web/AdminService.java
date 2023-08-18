package com.karenmm.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	AdminDAO adminDAO;
	
	public Map<String, Object> adminLogin(Map<String, Object> map) {

		return adminDAO.adminLogin(map);
	}

}
