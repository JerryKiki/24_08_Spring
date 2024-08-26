package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public ResultData writeArticle(int loginedMemberId, String title, String body, int boardId) {
		articleRepository.writeArticle(loginedMemberId, title, body, boardId);

		int id = articleRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 글이 등록되었습니다", id), "등록된 게시글 번호", id);
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

	public List<Article> getArticles(int boardId) {
		return articleRepository.getArticles(boardId);
	}
	
	public List<Article> getArticlesByPage(int boardId, int limitFrom, int limitTake) {
		return articleRepository.getArticlesByPage(boardId, limitFrom, limitTake);
	}

	public List<Article> getSearchedArticles(int boardId, String searchItem, String searchKeyword) {
		return articleRepository.getSearchedArticles(boardId, searchItem, searchKeyword);
	}
	
	public List<Article> getSearchedArticlesByPage(int boardId, int limitFrom, int limitTake, String searchItem, String searchKeyword) {
		return articleRepository.getSearchedArticlesByPage(boardId,limitFrom, limitTake, searchItem, searchKeyword);
	}

	public ResultData addView(int id) {
		int affectedRow = articleRepository.addView(id);
		if (affectedRow == 0) {
			return ResultData.from("F-1", "해당 게시글 없음", "id", id);
		}
		return ResultData.from("S-1", "해당 게시글 조회수 증가", "id", id);
		
	}
	
	public Object getArticleViewCount(int id) {
		return articleRepository.getArticleViewCount(id);
	}

	//좋아요 수
	public void updateArticleLikeCount(int articleId, boolean alreadyActioned, int point) {
		if(alreadyActioned) { //이미 반응했다면
			if(point==1) {
				//포인트가 1(좋아요)라면 아티클의 좋아요 수 -1
				articleRepository.minusLike(articleId);
			} else if (point == -1) {
				//포인트가 -1(싫어요)라면 아티클의 좋아요 수 +1
				articleRepository.addLike(articleId);
			}
		} else { //좋아요 기록이 없다면 등록
			if(point==1) { //1(좋아요)라면 +1
				articleRepository.addLike(articleId);
			}else if (point == -1) { //-1(싫어요)라면 -1
				articleRepository.minusLike(articleId);
			}
		}
	}

	public int getArticleLikeCountById(int id) {
		return articleRepository.getArticleLikeCountById(id);
	}

	public void updateArticleLikeCountByToggle(int articleId, int point) {
		if(point == 1) { //싫어요=>좋아요라면 +1 +1
			articleRepository.addLike(articleId);
			articleRepository.addLike(articleId);
		} else if(point == -1) { //좋아요=>싫어요라면 -1 -1
			articleRepository.minusLike(articleId);
			articleRepository.minusLike(articleId);
		}
	}

}

//@Service
//public class ArticleService {
//	@Autowired
//	private ArticleRepository articleRepository;
//
//	public ArticleService(ArticleRepository articleRepository) {
//		this.articleRepository = articleRepository;
////		makeTestData();
//	}
//
////	private void makeTestData() {
////		for (int i = 1; i <= 10; i++) {
////			String title = "제목" + i;
////			String body = "내용" + i;
////
////			articleRepository.writeArticle(title, body);
////		}
////	}
//
//	public int writeArticle(String title, String body) {
//		articleRepository.writeArticle(title, body);
//		return articleRepository.getLastInsertId();
//	}
//
//	public void deleteArticle(int id) {
//		articleRepository.deleteArticle(id);
//	}
//
//	public void modifyArticle(int id, String title, String body) {
//		articleRepository.modifyArticle(id, title, body);
//	}
//
//	public Article getArticleById(int id) {
//		return articleRepository.getArticleById(id);
//	}
//
//	public List<Article> getArticles() {
//		return articleRepository.getArticles();
//	}
//}

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
