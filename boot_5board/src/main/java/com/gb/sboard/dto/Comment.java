package com.gb.sboard.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
	private Integer idx;
	private int mref;
	private String name;
	private String password;
	private String content;
	private LocalDateTime wdate;
	private String ip;
}
