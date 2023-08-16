package com.karenmm.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private LoginDAO loginDAO;
	
	public Map<String, Object> login(Map<String, String> map) {
		return loginDAO.login(map);
	}

}
