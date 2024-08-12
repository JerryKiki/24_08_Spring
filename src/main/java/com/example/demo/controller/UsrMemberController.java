package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		int memberCheck = 0;
		
		if(Ut.isEmptyOrNull(loginId)) return ResultData.from("F-5", "필수 입력 사항을 모두 입력해주세요.", "아이디 미입력");
		else {
			memberCheck = memberService.getMemberByLoginId(loginId);
			if (memberCheck==-1) return ResultData.from("F-6", Ut.f("이미 사용중인 아이디(%s)입니다", loginId), "중복된 아이디");
			//"이미 사용중인 아이디입니다. (" + loginId + ")";
		}
		
		if(Ut.isEmptyOrNull(loginPw)) return ResultData.from("F-5", "필수 입력 사항을 모두 입력해주세요.", "패스워드 미입력");
		if(Ut.isEmptyOrNull(name)) return ResultData.from("F-5", "필수 입력 사항을 모두 입력해주세요.", "이름 미입력");
		if(Ut.isEmptyOrNull(nickname)) return ResultData.from("F-5", "필수 입력 사항을 모두 입력해주세요.", "닉네임 미입력");
		if(Ut.isEmptyOrNull(cellphoneNum)) return ResultData.from("F-5", "필수 입력 사항을 모두 입력해주세요.", "전화번호 미입력");
		if(Ut.isEmptyOrNull(email)) return ResultData.from("F-5", "필수 입력 사항을 모두 입력해주세요.", "이메일 미입력");
		if(!email.contains("@")) return ResultData.from("F-7", "올바른 이메일을 입력하세요.", "잘못된 이메일 형식:" + email);
		
		memberCheck = memberService.getMemberByNameAndEmail(name, email);
		if (memberCheck==-1) return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일(%s)로 이미 가입된 내역이 있습니다.", name, email), "중복 가입");
		
		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		Member member = memberService.getMemberById(id);
		return ResultData.from("S-6", Ut.f("회원가입에 성공하였습니다. %s님 환영합니다.", nickname), member);
	}

}

/*
 ResultData => 표준 메뉴얼(보고서)
 
 요청<->응답
 
 사장<->개발자
 
 유저<->브라우저
 브라우저<->컨트롤러
 컨트롤러<->서비스
 서비스<->리포지터리
 리포지터리<->DB
 
 표준 보고서가 지켜야 할 사항 or 있어야 할 기능
 - 성공, 실패 여부를 쉽게 파악할 수 있도록
 - 관련 데이터를 주고 받을 수 있도록
 - (관련 메세지를 포함)
 
 [성공/실패, 메세지, 추가데이터]
 */

