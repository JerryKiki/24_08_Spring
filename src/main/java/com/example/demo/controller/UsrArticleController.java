package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));

		}

		return ResultData.from("S-1", Ut.f("%d번 게시글 입니다", id), article);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpSession httpSession, int id, String title, String body) {
		
		if (httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후에만 이용할 수 있습니다.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}
		
		if((int) httpSession.getAttribute("loginedMemberId") != article.getAuthor()) {
			return ResultData.from("F-2", Ut.f("%d번 게시글에 대한 수정 권한이 없습니다", id), article);
		}

		articleService.modifyArticle(id, title, body);

		article = articleService.getArticleById(id);

		return ResultData.from("S-1", Ut.f("%d번 게시글을 수정했습니다", id), article);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Article> doDelete(HttpSession httpSession, int id) {
		
		if (httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후에만 이용할 수 있습니다.");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}
		
		if((int) httpSession.getAttribute("loginedMemberId") != article.getAuthor()) {
			return ResultData.from("F-2", Ut.f("%d번 게시글에 대한 삭제 권한이 없습니다", id), article);
		}

		articleService.deleteArticle(id);

		return ResultData.from("S-1", Ut.f("%d번 게시글을 삭제했습니다", id), article);
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpSession httpSession, String title, String body) {
		
		if (httpSession.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후에만 이용할 수 있습니다.");
		}

		if (Ut.isEmptyOrNull(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		
		if (Ut.isEmptyOrNull(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}
		
		int loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");

		ResultData writeArticleRd = articleService.writeArticle(loginedMemberId, title, body);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticleById(id);

		return ResultData.newData(writeArticleRd, article);
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "Article List", articles);
	}

}

//@Controller
//public class UsrArticleController {
//	
//	@Autowired
//	private ArticleService articleService;
//
//	@RequestMapping("/usr/article/getArticle")
//	@ResponseBody
//	public ResultData getArticle(int id) {
//
//		Article article = articleService.getArticleById(id);
//
//		if (article == null) {
////			return id + "번 글은 없음";
//			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
//		}
//
//		return ResultData.from("S-1", Ut.f("%d번 게시글입니다.", id), article);
//	}
//
//	@RequestMapping("/usr/article/doModify")
//	@ResponseBody
//	public ResultData doModify(int id, String title, String body) {
//
//		Article article = articleService.getArticleById(id);
//
//		if (article == null) {
//			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
//		}
//
//		articleService.modifyArticle(id, title, body);
//
//		return ResultData.from("S-2", Ut.f("%d번 게시글이 수정되었습니다.", id), article);
//	}
//
//	@RequestMapping("/usr/article/doDelete")
//	@ResponseBody
//	public ResultData doDelete(int id) {
//
//		Article article = articleService.getArticleById(id);
//
//		if (article == null) {
//			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
//		}
//
//		articleService.deleteArticle(id);
//
//		return ResultData.from("S-3", Ut.f("%d번 게시글이 삭제되었습니다.", id), article);
//	}
//
//	@RequestMapping("/usr/article/doWrite")
//	@ResponseBody
//	public ResultData doWrite(String title, String body) {
//		
//		int insertedId = -1;
//		
//		try {
//			insertedId = articleService.writeArticle(title, body);
//		} catch (Exception e) {
//			if(title==null) {
//				return ResultData.from("F-2", Ut.f("게시글 생성에 실패하였습니다. title을 입력하세요."), "제목 미입력");
//			}
//			
//			if(body==null) {
//				return ResultData.from("F-3", Ut.f("게시글 생성에 실패하였습니다. body를 입력하세요."), "내용 미입력");
//			}
//			
//			else return ResultData.from("F-4", Ut.f("게시글 생성에 실패하였습니다."), "원인 불명");
//		}
//		
//		Article article = articleService.getArticleById(insertedId);
//		return ResultData.from("S-4", Ut.f("%d번 게시글이 생성되었습니다.", insertedId), article);
//	}
//
//	@RequestMapping("/usr/article/getArticles")
//	@ResponseBody
//	public ResultData getArticles() {
//		return ResultData.from("S-5", Ut.f("전체 게시글 목록입니다."), articleService.getArticles());
//	}
//}
	
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
