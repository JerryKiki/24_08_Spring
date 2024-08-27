package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.service.ReplyService;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Reply;

import jakarta.servlet.http.HttpSession;




@Controller
public class UsrReplyController {
	
	@Autowired
	private ReplyService replyService;

	@RequestMapping("/usr/reply/writeReply")
	@ResponseBody
	public ResultData writeReply(int memberId, int articleId, String body) {
		
		ResultData writeReplyRd = replyService.writeReply(memberId, articleId, body);
		int lastInsertedReplyId = replyService.getLastInsertId();
		
		Reply newReply = replyService.getReplyById(lastInsertedReplyId);
		
		return ResultData.newData(writeReplyRd, "newReply", newReply);
	}
	
//	@RequestMapping("/usr/reply/modifyReply")
//	public ResultData modifyReply(int memberId, int articleId, String body) {
//		
//		ResultData writeReplyRd = replyService.writeReply(memberId, articleId, body);
//		int lastInsertedReplyId = replyService.getLastInsertId();
//		
//		Reply newReply = replyService.getReplyById(lastInsertedReplyId);
//		
//		return ResultData.newData(writeReplyRd, "newReply", newReply);
//	}
	
	@RequestMapping("/usr/reply/deleteReply")
	@ResponseBody
	public ResultData deleteReply(int id, int memberId, HttpSession httpSession) {
		
		//로그인체크
		if(httpSession.getAttribute("loginedMemberid") == null) { //로그인 체크
			return ResultData.from("F-A", "로그인 후 이용하세요.");
		}
		
		//Reply 권한체크
		Reply thisReply = replyService.getReplyById(id);
		if(thisReply.getMemberId() != memberId) {
			return ResultData.from("F-1", "해당 댓글에 대한 권한이 없습니다.");
		}
		
		//실제 삭제
		ResultData deleteReplyRd = replyService.doDeleteReply(id);
		
		//데이터 전송
		return deleteReplyRd;
	}

}
