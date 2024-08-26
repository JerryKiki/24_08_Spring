package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Likes;

@Mapper
public interface LikeRepository {

	@Select("SELECT * FROM likes WHERE memberId = #{loginedMemberId}")
	List<Likes> getArticleActionHistoryByMemberId(int loginedMemberId);

	@Delete("DELETE FROM likes WHERE relTypeCode = 1 AND relId = #{articleId} AND memberId = #{loginedMemberId}")
	void removeLikeHistoryOfArticle(int articleId, int loginedMemberId);

	@Insert("INSERT INTO likes SET regDate = NOW(), relTypeCode = 1, relId = #{articleId}, memberId = #{loginedMemberId}, `point` = ${point}")
	void addLikeHistoryOfArticle(int articleId, int loginedMemberId, int point);

	@Select("SELECT * FROM likes WHERE memberId = #{loginedMemberId} AND relId = #{id} AND relTypeCode = 1")
	Likes getArticleActionHistoryByIds(int loginedMemberId, int id);

	@Update("UPDATE likes SET `point` = ${point} WHERE memberId = #{loginedMemberId} AND relId = #{articleId} AND relTypeCode = 1")
	void updateLikeHistoryOfArticle(int articleId, int loginedMemberId, int point);
}
