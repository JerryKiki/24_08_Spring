package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 글은 없음";
		}

		return article;
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 글은 없음";
		}

		articleService.modifyArticle(id, title, body);

		return article;
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return id + "번 글은 없음";
		}

		articleService.deleteArticle(id);

		return id + "번 글이 삭제됨";
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = articleService.writeArticle(title, body);
		return article;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articleService.getArticles();
	}
}
	
//	@Autowired
//	private ArticleService articleService;
//	
//	@RequestMapping("/usr/home/doAdd")
//	@ResponseBody
//	public String doAdd(String title, String body) {
//		
//		return articleService.writeArticle(title, body);
//	}
//	
//	@RequestMapping("/usr/home/getArticles")
//	@ResponseBody
//	public Object getArticles() {
//		return articleService.showList();
//	}
//	
//	@RequestMapping("/usr/home/doDelete")
//	@ResponseBody
//	public String doDelete(int id) {
//		
//		return articleService.deleteArticle(id);
//	}
//	
//	
//	@RequestMapping("/usr/home/doModify")
//	@ResponseBody
//	public String doModify(int id, String title, String body) {
//		
//		return articleService.updateArticle(id, title, body);
//	}
//	
//	@RequestMapping("/usr/home/getDetail")
//	@ResponseBody
//	public Object getDetail(int id) {
//		
//		Article article = articleService.getArticlebyId(id);
//		
//		if(article == null) return id + "번 게시글은 없습니다.";
//		
//		String detail = article.toString();
//		return "Article 정보<br>" + detail;
//	}
