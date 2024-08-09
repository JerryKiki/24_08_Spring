package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.vo.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
//		makeTestData();
	}

//	private void makeTestData() {
//		for (int i = 1; i <= 10; i++) {
//			String title = "제목" + i;
//			String body = "내용" + i;
//
//			articleRepository.writeArticle(title, body);
//		}
//	}

	public Article writeArticle(String title, String body) {
		return articleRepository.writeArticle(title, body);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

	public Article getArticleById(int id) {

		return articleRepository.getArticleById(id);
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}
}

//	@Autowired
//	private ArticleRepository articleRepositoy;
//	
//	ArticleService(ArticleRepository articleRepositoy) {
//		this.articleRepositoy = articleRepositoy;
//		//생성과 동시에 테스트 데이터 생성
//		makeTestData();
//	}
//	
//	public void makeTestData() {
//
//		for(int i = 0; i < 10; i++) {
//			int id = lastArticleId + 1;
//			String title = String.format("제목%d", id);
//			String body = String.format("내용%d", id);
//			Article article = new Article(id, title, body);
//			articles.add(article);
//			lastArticleId++;
//		}
//	}
//	
//	public Article getArticlebyId(int id) {
//		
//		Article article = null;
//		
//		for(int i = 0; i < this.articles.size(); i++) {
//			if(articles.get(i).getId() == id) {
//				article = articles.get(i);
//			}
//		}
//		
//		return article;
//	}
//	
//	public String writeArticle(String title, String body) {
//		int id = lastArticleId + 1;
//		Article article = new Article(id, title, body);
//		articles.add(article);
//		lastArticleId++;
//		return String.format("%d번 게시글 생성됨", id);
//	}
//	
//	public Object showList() {
//		return articles;
//	}
//	
//	public String deleteArticle(int id) {
//		
//		String rs = "";
//		
//		Article article = getArticlebyId(id);
//		
//		if (article == null) {
//			rs = String.format("%d번 게시글은 없습니다.", id);
//		} else {
//			articles.remove(article);
//			rs = String.format("%d번 글이 삭제되었습니다.", id);
//		}
//		
//		return rs;
//	}
//	
//	public String updateArticle(int id, String title, String body) {
//		
//		if (title == null) return "title을 입력하세요.";
//		if (body == null) return "body를 입력하세요.";
//		
//		Article article = getArticlebyId(id);
//		
//		if (article == null) {
//			return String.format("%d번 게시글은 없습니다.", id);
//		} else {
//			article.setTitle(title);
//			article.setBody(body);
//			
//			String updateInfo = article.toString();
//			
//			//리턴 타입을 Object로 하면 toString을 쓰지 않고도 바로 +만 해서 리턴할 수 있다
//			return String.format("%d번 글이 수정되었습니다.<br>", id) + "수정 결과 : " + updateInfo;
//		}
//	}
