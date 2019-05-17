package com.busanit.bbs.mapper;

import java.util.List;

import com.busanit.bbs.dto.BoardAttachDto;

public interface BoardAttachMapper {
	
	public void insert(BoardAttachDto dto);
	
	public void delete(String uuid);
	
	public List<BoardAttachDto> findByBno(Long bno);
	
	public void deleteAll(Long bno);
	
	public List<BoardAttachDto> getOldFiles();
	
}
