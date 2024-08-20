package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int author;
	private String nickname;
	private int boardId;
	private String code;
	private int view;
	private int like;
}
	
//	public String toString() {
//		return "<br>" + "id = " + id + "<br>" + "title = " + title + "<br>" + "body = " + body;
//	}
