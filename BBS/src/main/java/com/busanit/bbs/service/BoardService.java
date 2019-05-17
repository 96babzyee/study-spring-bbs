package com.busanit.bbs.service;

import java.util.List;

import com.busanit.bbs.dto.BoardAttachDto;
import com.busanit.bbs.dto.BoardDto;
import com.busanit.bbs.dto.Criteria;

public interface BoardService {

	public void register(BoardDto board);

	public BoardDto get(Long bno);

	public boolean modify(BoardDto board);

	public boolean remove(Long bno);

	public List<BoardDto> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	public List<BoardAttachDto> getAttachList(Long bno);
	
}
