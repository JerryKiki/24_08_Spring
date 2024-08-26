package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.vo.Reply;
import com.example.demo.repository.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;

	public List<Reply> getRepliesByArticleId(int articleId) {
		return replyRepository.getRepliesByArticleId(articleId);
	}

}
