package com.busanit.bbs.dto;


import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardDto {
	
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	
	private int replyCnt;
	
	private List<BoardAttachDto> attachList;

}
