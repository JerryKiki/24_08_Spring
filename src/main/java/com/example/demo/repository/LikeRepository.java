package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Likes;

@Mapper
public interface LikeRepository {

	@Select("SELECT * FROM likes WHERE userId = #{loginedMemberId}")
	List<Likes> getHistoryByUserId(int loginedMemberId);

	@Delete("DELETE FROM likes WHERE articleId = #{articleId} AND userId = #{loginedMemberId}")
	void removeLikeHistory(int articleId, int loginedMemberId);

	@Insert("INSERT INTO likes SET regDate = NOW(), articleId = #{articleId}, userId = #{loginedMemberId}")
	void addLikeHistory(int articleId, int loginedMemberId);
}
