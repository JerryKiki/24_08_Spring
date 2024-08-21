package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Likes;

@Mapper
public interface LikeRepository {

	@Select("SELECT * FROM likes WHERE memberId = #{loginedMemberId}")
	List<Likes> getHistoryByMemberId(int loginedMemberId);

	@Delete("DELETE FROM likes WHERE relId = #{articleId} AND memberId = #{loginedMemberId}")
	void removeLikeHistoryOfArticle(int articleId, int loginedMemberId);

	@Insert("INSERT INTO likes SET regDate = NOW(), relTypeCode = 1, relId = #{articleId}, memberId = #{loginedMemberId}")
	void addLikeHistoryOfArticle(int articleId, int loginedMemberId);
}
