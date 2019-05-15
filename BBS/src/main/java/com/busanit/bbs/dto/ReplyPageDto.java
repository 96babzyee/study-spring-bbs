package com.busanit.bbs.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDto {
	
	private int replyCnt;
	private List<ReplyDto> list;
	
}
