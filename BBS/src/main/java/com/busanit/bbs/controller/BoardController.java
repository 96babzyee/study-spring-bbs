package com.busanit.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.busanit.bbs.dto.BoardDto;
import com.busanit.bbs.dto.Criteria;
import com.busanit.bbs.dto.PageDto;
import com.busanit.bbs.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService service;

	// 게시물 등록 Form
	@GetMapping("/register")
	public void register() {}
	// 입력Form을 보여주는 역할을 하기때문에 별도의 처리 필요 X}
	
	// 게시물 등록
	@PostMapping("/register")
	public String register(BoardDto board, RedirectAttributes rttr) {
		log.info("register : " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}

	// 게시판 전체 목록
	/*@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
	}*/
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list : " + cri);
		model.addAttribute("list", service.getList(cri));
		
		int total = service.getTotal(cri);
		log.info("total : "+total);
		model.addAttribute("pageMaker", new PageDto(cri, total));
	}
	
	// 특정 게시물 조회 or 게시물 수정 Form
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno,
			@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}

	// 게시물 수정후 게시판 이동
	@PostMapping("/modify")
	public String modify(BoardDto board, @ModelAttribute("cri") Criteria cri,
			RedirectAttributes rttr) {
		log.info("modify : " + board);
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	// 게시물 삭제
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno,
			@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove : " + bno);
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}

}