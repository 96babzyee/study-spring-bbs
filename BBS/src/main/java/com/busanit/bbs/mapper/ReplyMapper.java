package com.busanit.bbs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.busanit.bbs.dto.Criteria;
import com.busanit.bbs.dto.ReplyDto;

public interface ReplyMapper {
	
	public int insert(ReplyDto dto);
	
	public ReplyDto select(Long bno);
	
	public int delete(Long rno);
	
	public int update(ReplyDto reply);
	
	public List<ReplyDto> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
	public int getCountByBno(Long bno);
	
}
