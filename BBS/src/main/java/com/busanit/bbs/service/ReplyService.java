package com.busanit.bbs.service;

import java.util.List;

import com.busanit.bbs.dto.Criteria;
import com.busanit.bbs.dto.ReplyDto;
import com.busanit.bbs.dto.ReplyPageDto;

public interface ReplyService {
	
	public int register(ReplyDto dto);
	
	public ReplyDto get(Long rno);
	
	public int modify(ReplyDto dto);
	
	public int remove(Long rno);
	
	public List<ReplyDto> getList(Criteria cri, Long bno);
	
	public ReplyPageDto getListPage(Criteria cri, Long bno);

}
