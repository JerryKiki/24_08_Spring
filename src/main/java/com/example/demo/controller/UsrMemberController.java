package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		if(loginId == null) return "아이디를 입력하세요.";
		else {
			Member memberCheck = memberService.getMemberByLoginId(loginId);
			if (memberCheck!=null) return "이미 사용중인 아이디입니다.";
		}
		
		if(loginPw == null) return "비밀번호를 입력하세요.";
		if(name == null) return "이름을 입력하세요.";
		if(nickname == null) return "닉네임을 입력하세요.";
		if(cellphoneNum == null) return "전화번호를 입력하세요.";
		if(email == null) return "이메일을 입력하세요.";
		if(!email.contains("@")) return "올바른 이메일을 입력하세요.";
		
		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		Member member = memberService.getMemberById(id);
		return member;
	}

}

