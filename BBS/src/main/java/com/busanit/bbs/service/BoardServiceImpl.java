package com.busanit.bbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.busanit.bbs.dto.BoardDto;
import com.busanit.bbs.dto.Criteria;
import com.busanit.bbs.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor // 모든 파라미터를 이용하는 생성자를 만듬
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;

	// 게시물 등록
	@Override
	public void register(BoardDto board) {
		log.info("register......" + board);
		mapper.insertSelectKey(board);
	}

	// 전체 리스트를 가져옴
	/*@Override
	 public List<BoardDto> getList() {
	 	log.info("getList..........");
	 	return mapper.getList();
	 }*/
	@Override
	 public List<BoardDto> getList(Criteria cri) {
	 	log.info("get list with criteria : " + cri);
	 	return mapper.getListWithPaging(cri);
	 }

	// 특정한 게시물을 가져옴
	@Override
	public BoardDto get(Long bno) {
		log.info("get......" + bno);
		return mapper.select(bno);
	}

	// 게시물 수정
	@Override
	public boolean modify(BoardDto board) {
		log.info("modify......" + board);
		return mapper.update(board) == 1;
	}

	// 게시물 삭제
	@Override
	public boolean remove(Long bno) {
		log.info("remove...." + bno);
		return mapper.delete(bno) == 1;
	}
	
	// myBatis 전체 데이터의 개수 처리
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

}
