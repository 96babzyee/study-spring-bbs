package com.busanit.bbs.mapper;

import java.util.List;

import com.busanit.bbs.dto.BoardDto;
import com.busanit.bbs.dto.Criteria;

public interface BoardMapper {
	public List<BoardDto> getList();
	
	public void insert(BoardDto board);
	
	public void insertSelectKey(BoardDto board);
	
	public BoardDto select(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardDto board);
	
	public List<BoardDto> getListWithPaging(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
}
