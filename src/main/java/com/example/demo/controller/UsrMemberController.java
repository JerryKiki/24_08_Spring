package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		int memberCheck = 0;
		
		if(Ut.isEmptyOrNull(loginId)) return "아이디를 입력하세요.";
		else {
			memberCheck = memberService.getMemberByLoginId(loginId);
			if (memberCheck==-1) return "이미 사용중인 아이디입니다.";
		}
		
		if(Ut.isEmptyOrNull(loginPw)) return "비밀번호를 입력하세요.";
		if(Ut.isEmptyOrNull(name)) return "이름을 입력하세요.";
		if(Ut.isEmptyOrNull(nickname)) return "닉네임을 입력하세요.";
		if(Ut.isEmptyOrNull(cellphoneNum)) return "전화번호를 입력하세요.";
		if(Ut.isEmptyOrNull(email)) return "이메일을 입력하세요.";
		if(!email.contains("@")) return "올바른 이메일을 입력하세요.";
		
		memberCheck = memberService.getMemberByNameAndEmail(name, email);
		if (memberCheck==-1) return "해당 이름과 이메일로 이미 가입된 내역이 있습니다.";
		
		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		Member member = memberService.getMemberById(id);
		return member;
	}

}

