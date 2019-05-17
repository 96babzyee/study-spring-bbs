package com.busanit.bbs.dto;

import lombok.Data;

@Data
public class AttachFileDto {

	private String fileName; // 원본 파일 이름
	private String uploadPath; // 업로드 경로
	private String uuid; // UUID 값
	private boolean image; // 이미지 여부

}
