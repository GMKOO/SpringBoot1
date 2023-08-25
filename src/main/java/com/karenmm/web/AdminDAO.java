package com.karenmm.web;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDAO {

	Map<String, Object> adminLogin(Map<String, Object> map);

	List<Map<String, Object>> list();

	void noticeWrite(Map<String, Object> map);

	String noticeDetail(int nno);

	int noticeHide(int nno);

	Map<String, Object> admultiboard();

	List<Map<String, Object>> setupBoardList();

	List<Map<String, Object>> member();

	int multiboard1(Map<String, Object> map);
	
	List<Map<String, Object>> memberlist();

	int gradeChange(Map<String, String> map);



	 

}

