package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.vo.Reply;
import com.example.demo.vo.ResultData;
import com.example.demo.repository.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;

	public List<Reply> getRepliesByArticleId(int articleId) {
		return replyRepository.getRepliesByArticleId(articleId);
	}

	public ResultData writeReply(int memberId, int relTypeCode, int relId, String body) {
		
		replyRepository.writeReply(memberId, relTypeCode, relId, body);
		
		return ResultData.from("S-1", "댓글 생성 성공", "생성된 댓글 ID", replyRepository.getLastInsertId());
	}
	
	public int getLastInsertId() {
		return replyRepository.getLastInsertId();
	}
	
	public Reply getReplyById(int id) {
		return replyRepository.getReplyById(id);
	}

	public ResultData doDeleteReply(int id) {
		
		replyRepository.doDeleteReply(id);
		
		return ResultData.from("S-1", "삭제 성공");
	}

	public ResultData doModifyReply(int id, String body) {
		
		int affectedRow = replyRepository.doModifyReply(id, body);
		
		if(affectedRow == 1) { //성공한거임
			Reply modifiedReply = replyRepository.getReplyById(id);
			return ResultData.from("S-1", "수정 성공", "수정된 댓글", modifiedReply);
		}
		
		return ResultData.from("F-1", "수정 실패");
	}
	
}
