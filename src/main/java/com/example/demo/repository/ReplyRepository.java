package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyRepository {

	@Select("""
			SELECT R.*, M.nickname
			FROM replies R
			INNER JOIN `member` M
			ON R.memberId = M.id
			WHERE R.articleId = #{articleId}
			""")
	public List<Reply> getRepliesByArticleId(int articleId);

	@Insert("""
			INSERT INTO replies
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			articleId = #{articleId},
			`body` = #{body}
			""")
	public void writeReply(int memberId, int articleId, String body);
	
	@Select("SELECT LAST_INSERT_ID();")
	public int getLastInsertId();

	@Select("""
			SELECT R.*, M.nickname
			FROM replies R
			INNER JOIN `member` M
			ON R.memberId = M.id
			WHERE R.id = #{id}
			""")
	public Reply getReplyById(int id);
}
