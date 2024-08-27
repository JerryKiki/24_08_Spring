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

}
