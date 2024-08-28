package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyRepository {

	@Select("""
			SELECT R.*, M.nickname
			FROM replies R
			INNER JOIN `member` M
			ON R.memberId = M.id
			WHERE R.relId = #{articleId} AND R.relTypeCode = 1
			""")
	public List<Reply> getRepliesByArticleId(int articleId);

	@Insert("""
			INSERT INTO replies
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			`body` = #{body}
			""")
	public void writeReply(int memberId, int relTypeCode, int relId, String body);
	
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

	@Delete("DELETE FROM replies WHERE id = #{id}")
	public void doDeleteReply(int id);

	@Update("UPDATE replies SET updateDate = NOW(), `body` = #{body} WHERE id = #{id}")
	public int doModifyReply(int id, String body);
}
