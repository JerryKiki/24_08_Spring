package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int articleId;
	private String body;
	private int like;
	private String nickname;
}
	
