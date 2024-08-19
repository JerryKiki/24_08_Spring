package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
//	@Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` = #{body}")
	public void writeArticle(int loginedMemberId, String title, String body, int boardId);

	@Delete("DELETE FROM article WHERE id = #{id}")
	public void deleteArticle(int id);

//	@Update("UPDATE article SET updateDate = NOW(), title = #{title}, `body` = #{body} WHERE id = #{id}")
	public void modifyArticle(int id, String title, String body);

//	@Select("SELECT * FROM article WHERE id = #{id}")
	public Article getArticleById(int id);

//	@Select("SELECT * FROM article ORDER BY id DESC")
	public List<Article> getArticles(int boardId);

	@Select("SELECT LAST_INSERT_ID();")
	public int getLastInsertId();

//	@Select("SELECT * FROM article WHERE boardId = #{boardId} ORDER BY id DESC LIMIT ${limitFrom}, ${limitTake}")
	public List<Article> getArticlesByPage(int boardId, int limitFrom, int limitTake);
}
	
//	@Insert("INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = #{title}, `body` = #{body}")
//	public Article writeArticle(String title, String body);
//
//	@Delete("DELETE FROM article WHERE id = #{id}")
//	public void deleteArticle(int id);
//
//	@Update("UPDATE article SET title = #{title}, `body` = #{body} WHERE id = #{id}")
//	public void modifyArticle(int id, String title, String body);
//
//	public Article getArticleById(int id);
//
//	public List<Article> getArticles();


//private int lastArticleId;
//public List<Article> articles;

//public ArticleRepository() {
//	lastArticleId = 0;
//	articles = new ArrayList<>();
//}

//public Article writeArticle(String title, String body) {
//	int id = lastArticleId + 1;
//	Article article = new Article(id, title, body);
//	articles.add(article);
//	lastArticleId++;
//	return article;
//
//}
//
//public void deleteArticle(int id) {
//	Article article = getArticleById(id);
//	articles.remove(article);
//
//}
//
//public void modifyArticle(int id, String title, String body) {
//	Article article = getArticleById(id);
//	article.setTitle(title);
//	article.setBody(body);
//
//}
//
//public Article getArticleById(int id) {
//	for (Article article : articles) {
//		if (article.getId() == id) {
//			return article;
//		}
//	}
//	return null;
//}
//
//public List<Article> getArticles() {
//	return articles;
//}
