package jcpdev.day2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDto {
	private int idx;
	private String name;
	private String password;
	private String subject;
	private String content;

}
