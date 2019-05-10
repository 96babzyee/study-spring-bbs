package com.busanit.bbs.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardDto {
	private long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
}
