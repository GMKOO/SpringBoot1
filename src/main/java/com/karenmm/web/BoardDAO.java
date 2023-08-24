package com.karenmm.web;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDAO {

	List<BoardDTO> boardList();

	BoardDTO detail(int bno);

	int write(BoardDTO dto);

	int readUP(int bno);

	BoardDTO detail2(int bno);

}
