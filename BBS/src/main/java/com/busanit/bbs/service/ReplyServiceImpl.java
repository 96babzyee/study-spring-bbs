package com.busanit.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busanit.bbs.dto.Criteria;
import com.busanit.bbs.dto.ReplyDto;
import com.busanit.bbs.dto.ReplyPageDto;
import com.busanit.bbs.mapper.BoardMapper;
import com.busanit.bbs.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public int register(ReplyDto dto) {
		log.info("register..." + dto);
		boardMapper.updateReplyCnt(dto.getBno(), 1);
		
		return mapper.insert(dto);
	}
	
	@Override
	public ReplyDto get(Long rno) {
		log.info("get..." + rno);
		
		return mapper.select(rno);
	}

	@Override
	public int modify(ReplyDto dto) {
		log.info("modify..." + dto);
		
		return mapper.update(dto);
	}
	
	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("remove..." + rno);
		
		ReplyDto dto = mapper.select(rno);
		boardMapper.updateReplyCnt(dto.getBno(), -1);
		
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyDto> getList(Criteria cri, Long bno) {
		log.info("get Reply List of a Board..." + bno);
		
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDto getListPage(Criteria cri, Long bno) {
		
		return new ReplyPageDto(
				mapper.getCountByBno(bno),
				mapper.getListWithPaging(cri, bno));
	}
	
}
