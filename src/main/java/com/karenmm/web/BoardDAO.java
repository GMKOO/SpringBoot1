package com.karenmm.web;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardDAO {

	List<BoardDTO> boardList();

	String detail(int bno);
	
	}


