package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private BoardService boardService;

	@RequestMapping("/usr/article/getArticle")
	public String getArticle(int id, HttpSession httpSession, Model model) {
		
		boolean noArticle = false;

		Article article = articleService.getArticleById(id);

		if (article == null) {
			noArticle = true;
			model.addAttribute("noArticle", noArticle);
			return "/usr/alert";
		}
		
		if(httpSession.getAttribute("loginedMemberId") != null) {
			model = setLoginInfoBySessionId(httpSession, model, article);
		}
		
		model.addAttribute("article", article);
		return "/usr/article/detail";
	}

	// 로그인 체크 -> 유무 체크 -> 권한 체크 -> 수정
	@RequestMapping("/usr/article/doModify")
	public String doModify(HttpSession httpSession, Model model, int id, String title, String body) {
		
		boolean isntLogined = false;
		boolean noArticle = false;
		boolean cannotAccess = false;
		boolean articleModified = false;
		
		if(title == null && body == null) {
			
			if (httpSession.getAttribute("loginedMemberId") == null) {
				isntLogined = true;
				model.addAttribute("isntLogined", isntLogined);
				return "/usr/alert";
			}
	
			Article Oldarticle = articleService.getArticleById(id);
	
			if (Oldarticle == null) {
				noArticle = true;
				model.addAttribute("noArticle", noArticle);
				//model.addAttribute("id", id);
				return "/usr/alert";
			}
			
			if((int) httpSession.getAttribute("loginedMemberId") != Oldarticle.getAuthor()) {
				cannotAccess = true;
				model.addAttribute("cannotAccess", cannotAccess);
				//model.addAttribute("id", id);
				return "/usr/alert";
			}
			
			model = setLoginInfoBySessionId(httpSession, model);
			model.addAttribute("Oldarticle", Oldarticle);
			return "/usr/article/modify";

		} else {
			
			articleService.modifyArticle(id, title, body);

			articleModified = true;
			model.addAttribute("articleModified", articleModified);
			return "/usr/alert";
		}		
	}

	@RequestMapping("/usr/article/doDelete")
	public String doDelete(HttpSession httpSession, Model model, int id) {
		
		boolean isntLogined = false;
		boolean noArticle = false;
		boolean cannotAccess = false;
		boolean articleDeleted = false;
		
		if (httpSession.getAttribute("loginedMemberId") == null) {
			isntLogined = true;
			model.addAttribute("isntLogined", isntLogined);
			return "/usr/alert";
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			noArticle = true;
			model.addAttribute("noArticle", noArticle);
			//model.addAttribute("id", id);
			return "/usr/alert";
		}
		
		if((int) httpSession.getAttribute("loginedMemberId") != article.getAuthor()) {
			cannotAccess = true;
			model.addAttribute("cannotAccess", cannotAccess);
			//model.addAttribute("id", id);
			return "/usr/alert";
		}

		articleService.deleteArticle(id);

		articleDeleted = true;
		model.addAttribute("articleDeleted", articleDeleted);
		return "/usr/alert";
	}
	
	@RequestMapping("usr/article/doWrite")
	public String doWrite(HttpSession httpSession, Model model, String title, String body, String boardId) {
		
		boolean isntLogined = false;
		boolean writeSuccess = false;
		
		if (httpSession.getAttribute("loginedMemberId") == null) {
			isntLogined = true;
			model.addAttribute("isntLogined", isntLogined);
			return "/usr/alert";
		}
		
		if (title==null || body==null) {
			model = setLoginInfoBySessionId(httpSession, model);
			return "/usr/article/write";
		}
		
		int loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		int boardIdInt = Integer.parseInt(boardId);

		ResultData writeArticleRd = articleService.writeArticle(loginedMemberId, title, body, boardIdInt);

		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticleById(id);
		
		writeSuccess = true;
		model.addAttribute("writeSuccess", writeSuccess);
		return "/usr/alert";
	}
	
	//Spring model 객체 찾아보기
	//인자 안들어왔을 때의 디폴트값 설정: 인자 앞에 @RequestParam(value="boardId", defaultValue = "-1")
	@RequestMapping("/usr/article/getArticles")
	public String getArticles(HttpSession httpSession, Model model, String boardId, String page, String searchItem, String searchKeyword) {
		//검색여부 확인할 변수
		boolean searched = false;
		boolean gotBoardId = false;
		
		//게시판 넘버 처리
		int boardIdInt = -1;
		if(boardId != null && !boardId.equals("-1")) {
			gotBoardId = true;
			boardIdInt = Integer.parseInt(boardId);
		}
		
		//페이지네이션
		int pageNum = 1;
		if(page != null) {
			pageNum = Integer.parseInt(page);
		}
		
		int itemsInPage = 10;
		int limitFrom = (pageNum - 1) * itemsInPage;
		int limitTake = itemsInPage;
		
		//아티클 불러오기
		List<Article> articles;
		if(searchItem != null && searchKeyword != null) {
			searched = true;
			articles = articleService.getSearchedArticles(boardIdInt, searchItem, "%" + searchKeyword + "%");
		}
		else {
			articles = articleService.getArticles(boardIdInt);
		}
		
		//경우의 수 확인
		Board board = null;
		if(gotBoardId) { //보드를 지정했다면
			board = boardService.getBoardById(boardIdInt); //보드 검사
			if(board == null) {//보드 자체가 없다면,
				model.addAttribute("noneBoard", true);
				return "usr/alert";
			} else if(board != null && articles.isEmpty()) { //보드는 있고 아티클은 없다면
				if(httpSession.getAttribute("loginedMemberId") != null) {
						model = setLoginInfoBySessionId(httpSession, model);
				}
				model.addAttribute("boardCode", board.getCode());
				model.addAttribute("noneArticle", true);
				return "/usr/article/list";
			}
		} else { //보드가 지정되지 않았다면
			if(articles.isEmpty()) { //아티클만 검사
				if(httpSession.getAttribute("loginedMemberId") != null) {
					model = setLoginInfoBySessionId(httpSession, model);
				}
				model.addAttribute("boardCode", "All");
				model.addAttribute("noneArticle", true);
				return "/usr/article/list";
			}
		}
		
		//모든 검사를 통과했다면(==> 둘다 있다면) 아래로
		//최대 페이지수 구하기
		int totalCount = articles.size();
		int maxPage = (int) Math.ceil(totalCount / (double)itemsInPage);
		
		//실제로 표시될 페이지 가져오기
		List<Article> displayArticles;
		if(searched) {
			displayArticles = articleService.getSearchedArticlesByPage(boardIdInt, limitFrom, limitTake, searchItem, "%" + searchKeyword + "%");
			model.addAttribute("searched", searched);
			model.addAttribute("searchItem", searchItem);
			model.addAttribute("searchKeyword", searchKeyword);
		} else {
			displayArticles = articleService.getArticlesByPage(boardIdInt, limitFrom, limitTake);
		}
		
		//표시될 페이지 넘버들
		int startNum = 1;
		int endNum = pageNum + 9 <= maxPage ? 10 : maxPage;
		
		if(pageNum > 4 && maxPage > 10) {
			startNum = pageNum - 4;
			if(startNum + 9 <= maxPage) {
				endNum = startNum + 9;
			} else {
				endNum = maxPage;
			}
		}
		
		//로그인한 멤버 정보
		if(httpSession.getAttribute("loginedMemberId") != null) {
			model = setLoginInfoBySessionId(httpSession, model);
		}
		
		
		model.addAttribute("articles", displayArticles);
		model.addAttribute("noneArticle", false);
		if(boardIdInt != -1) {
			model.addAttribute("boardCode", board.getCode());
			model.addAttribute("boardId", board.getId());
		} else {
			model.addAttribute("boardCode", "All");
			model.addAttribute("boardId", boardIdInt);
		}
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("startNum", startNum);
		model.addAttribute("endNum", endNum);
		return "/usr/article/list";
	}
	
	public Model setLoginInfoBySessionId(HttpSession httpSession, Model model) {
		int loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		Member loginedMember = memberService.getMemberById(loginedMemberId);
		model.addAttribute("isLogined", true);
		model.addAttribute("loginedMember", loginedMember);
		
		return model;
	}
	
	public Model setLoginInfoBySessionId(HttpSession httpSession, Model model, Article article) {
		int loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		Member loginedMember = memberService.getMemberById(loginedMemberId);
		model.addAttribute("isLogined", true);
		model.addAttribute("loginedMember", loginedMember);
		
		if(article.getAuthor() == loginedMemberId) {
			model.addAttribute("canAccess", true);
		}
		
		return model;
	}

//	@RequestMapping("/usr/article/getArticles")
//	@ResponseBody
//	public ResultData<List<Article>> getArticles() {
//		List<Article> articles = articleService.getArticles();
//		return ResultData.from("S-1", "Article List", "게시글 목록", articles);
//	}
	


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
