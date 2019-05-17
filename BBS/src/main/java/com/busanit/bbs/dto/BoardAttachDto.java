package com.busanit.bbs.dto;

import lombok.Data;

@Data
public class BoardAttachDto {
	
	private String uuid; // UUID 값
	private String uploadPath; // 업로드 경로
	private String fileName; // 원본 파일 이름
	private boolean fileType; // 이미지 여부
	private Long bno; // 게시물 번호

}
