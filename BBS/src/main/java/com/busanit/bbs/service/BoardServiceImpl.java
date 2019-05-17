package com.busanit.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busanit.bbs.dto.BoardAttachDto;
import com.busanit.bbs.dto.BoardDto;
import com.busanit.bbs.dto.Criteria;
import com.busanit.bbs.mapper.BoardAttachMapper;
import com.busanit.bbs.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor // 모든 파라미터를 이용하는 생성자를 만듬
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;

	// 게시물 등록
	@Transactional
	@Override
	public void register(BoardDto board) {
		log.info("register......" + board);
		mapper.insertSelectKey(board);

		if (board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}

	// 전체 리스트를 가져옴
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
	@Transactional
	@Override
	public boolean modify(BoardDto board) {
		log.info("modify......" + board);
		attachMapper.deleteAll(board.getBno());
		boolean modifyResult = mapper.update(board) == 1;

		if (modifyResult && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}
		return modifyResult;
	}

	// 게시물 삭제
	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("remove...." + bno);
		attachMapper.deleteAll(bno);
		return mapper.delete(bno) == 1;
	}

	// myBatis 전체 데이터의 개수 처리
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	// 게시물 첨부파일 목록
	@Override
	public List<BoardAttachDto> getAttachList(Long bno) {
		log.info("get Attach list by bno : " + bno);
		return attachMapper.findByBno(bno);
	}

}
