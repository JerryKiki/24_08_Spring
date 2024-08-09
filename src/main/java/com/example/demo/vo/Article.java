package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	
	int id;
	String title;
	String body;
	
	
	public String toString() {
		return "<br>" + "id = " + id + "<br>" + "title = " + title + "<br>" + "body = " + body;
	}
}
