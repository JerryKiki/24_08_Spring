package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller
public class UserArticleController {
	
	int lastArticleId;
	List<Article> articles;
	
	public UserArticleController() {
		lastArticleId = 0;
		articles = new ArrayList<>();
		
		//생성과 동시에 테스트 데이터 생성
		for(int i = 0; i < 10; i++) {
			int id = lastArticleId + 1;
			String title = String.format("제목%d", id);
			String body = String.format("내용%d", id);
			Article article = new Article(id, title, body);
			articles.add(article);
			lastArticleId++;
		}
	}
	
	@RequestMapping("/usr/home/doAdd")
	@ResponseBody
	public String doAdd(String title, String body) {
		int id = lastArticleId + 1;
		Article article = new Article(id, title, body);
		articles.add(article);
		lastArticleId++;
		return String.format("%d번 게시글 생성됨", id);
	}
	
	@RequestMapping("/usr/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articles;
	}
	
	@RequestMapping("/usr/home/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		String rs = "";
		
		int idx = -1;
		for(int i = 0; i < this.articles.size(); i++) {
			if(articles.get(i).getId() == id) {
				idx = i;
			}
		}
		
		if (idx==-1) {
			rs = String.format("%d번 게시글은 없습니다.", id);
		} else {
			articles.remove(idx);
			rs = String.format("%d번 글이 삭제되었습니다.", id);
		}
		
		return rs;
	}
	
	
	@RequestMapping("/usr/home/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		if (title == null) return "title을 입력하세요.";
		if (body == null) return "body를 입력하세요.";
		
		int idx = -1;
		for(int i = 0; i < this.articles.size(); i++) {
			if(articles.get(i).getId() == id) {
				idx = i;
			}
		}
		
		if (idx==-1) {
			return String.format("%d번 게시글은 없습니다.", id);
		} else {
			Article newArticle = articles.get(idx);
			newArticle.setTitle(title);
			newArticle.setBody(body);
			
			String nowArticle = newArticle.toString();
			
			//리턴 타입을 Object로 하면 toString을 쓰지 않고도 바로 +만 해서 리턴할 수 있다
			return String.format("%d번 글이 수정되었습니다.<br>", id) + "수정 결과 : " + nowArticle;
		}
	}
	
	
	
	
	
	
	
}
