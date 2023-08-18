package com.karenmm.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	
	
	@GetMapping("/notice")
	public String notice(Model model)	{
		//필요한거? model + map + service + DAO +Mapper
		
		List<Map<String,Object>> list =noticeService.list();
		model.addAttribute("list", list);
		
		return "notice";
	}
	
}
