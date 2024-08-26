package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.service.ReplyService;


@Controller
public class UsrReplyController {
	
	@Autowired
	private ReplyService replyService;

}
