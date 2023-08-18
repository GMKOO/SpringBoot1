package com.karenmm.web;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeDAO {

	

	public List<Map<String, Object>> list(); 
		
	

}
