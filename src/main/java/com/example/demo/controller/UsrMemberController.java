package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.LoginSession;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doLogout")
	public String doLogout(HttpSession httpSession, Model model) {

		boolean alreadyLogouted = false;
		boolean logoutSuccess = false;

		if (httpSession.getAttribute("loginedMemberId") == null) {
			alreadyLogouted = true;
			model.addAttribute("alreadyLogouted", alreadyLogouted);
			return "/usr/alert";
		}

		httpSession.removeAttribute("loginedMemberId");
		
		logoutSuccess = true;
		model.addAttribute("logoutSuccess", logoutSuccess);

		return "/usr/alert";
	}

	@RequestMapping("/usr/member/doLogin")
	public String doLogin(HttpSession httpSession, Model model, String loginId, String loginPw) {

		boolean alreadyLogined = false;
		boolean memberNotExists = false;
		boolean pwMismatch = false;
		boolean loginSuccess = false;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			alreadyLogined = true;
			model.addAttribute("alreadyLogined", alreadyLogined);
			return "/usr/alert";
		}
		
		if(loginId == null && loginPw == null) {
			return "/usr/member/login";
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			memberNotExists = true;
			model.addAttribute("memberNotExists", memberNotExists);
			return "/usr/alert";
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			pwMismatch = true;
			model.addAttribute("pwMismatch", pwMismatch);
			return "/usr/alert";
		}

		httpSession.setAttribute("loginedMemberId", member.getId());
		
		loginSuccess = true;
		model.addAttribute("loginSuccess", loginSuccess);

		return "/usr/alert";
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(HttpSession httpSession, String loginId, String loginPw, String name,
			String nickname, String cellphoneNum, String email) {

		boolean isLogined = false;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}

		if (isLogined) {
			return ResultData.from("F-A", "이미 로그인 함");
		}

		if (Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", "loginId 입력 x");
		}
		if (Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", "loginPw 입력 x");
		}
		if (Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-3", "name 입력 x");
		}
		if (Ut.isEmptyOrNull(nickname)) {
			return ResultData.from("F-4", "nickname 입력 x");
		}
		if (Ut.isEmptyOrNull(cellphoneNum)) {
			return ResultData.from("F-5", "cellphoneNum 입력 x");
		}
		if (Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-6", "email 입력 x");
		}

		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (doJoinRd.isFail()) {
			return doJoinRd;
		}

		Member member = memberService.getMemberById((int) doJoinRd.getData1());

		return ResultData.newData(doJoinRd, "새로 생성된 member", member);
	}
}

//@Controller
//public class UsrMemberController {
//
//	@Autowired
//	private MemberService memberService;
//
//	@RequestMapping("/usr/member/doJoin")
//	@ResponseBody
//	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
//			String email) {
//
//		if (Ut.isEmptyOrNull(loginId)) {
//			return ResultData.from("F-1", "loginId 입력 x");
//		}
//		if (Ut.isEmptyOrNull(loginPw)) {
//			return ResultData.from("F-2", "loginPw 입력 x");
//		}
//		if (Ut.isEmptyOrNull(name)) {
//			return ResultData.from("F-3", "name 입력 x");
//		}
//		if (Ut.isEmptyOrNull(nickname)) {
//			return ResultData.from("F-4", "nickname 입력 x");
//		}
//		if (Ut.isEmptyOrNull(cellphoneNum)) {
//			return ResultData.from("F-5", "cellphoneNum 입력 x");
//		}
//		if (Ut.isEmptyOrNull(email)) {
//			return ResultData.from("F-6", "email 입력 x");
//		}
//
//		ResultData doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
//
//		if (doJoinRd.isFail()) {
//			return doJoinRd;
//		}
//
//		Member member = memberService.getMemberById((int) doJoinRd.getData1());
//
//		return ResultData.newData(doJoinRd, "가입자 정보", member);
//	}
//	
//	@RequestMapping("/usr/member/doLogin")
//	@ResponseBody
//	public ResultData<Member> doLogin(HttpSession httpSession, String loginId, String loginPw) {
//		
//		if(httpSession.getAttribute("loginedMemberId") != null) {
//			Member loginedMember = (Member) httpSession.getAttribute("loginedMember");
//			return ResultData.from("F-1", Ut.f("이미 로그인 되어 있습니다. (%s)", loginedMember.getLoginId()), "로그인 정보", loginedMember);
//		}
//		
//		if (Ut.isEmptyOrNull(loginId)) {
//			return ResultData.from("F-2", "loginId 입력 X");
//		}
//		if (Ut.isEmptyOrNull(loginPw)) {
//			return ResultData.from("F-3", "loginPw 입력 x");
//		}
//		
//		Member existsMember = memberService.getMemberByLoginId(loginId);
//		
//		if (existsMember == null) {
//			return ResultData.from("F-4", Ut.f("존재하지 않는 아이디(%s)입니다.", loginId));
//		}
//		
//		if(!existsMember.getLoginPw().equals(loginPw)) {
//			return ResultData.from("F-5", "비밀번호가 틀립니다.");
//		}
//		
//		httpSession.setAttribute("loginedMemberId", existsMember.getId());
//		httpSession.setAttribute("loginedMember", existsMember);
////		LoginSession.setLoginStatus(existsMember, existsMember.getId());
//		
//		return ResultData.from("S-1", Ut.f("%s님 로그인 성공!", existsMember.getNickname()), "로그인 정보", existsMember);
//		
//	}
//	
//	@RequestMapping("/usr/member/doLogout")
//	@ResponseBody
//	public ResultData doLogout(HttpSession httpSession) {
//		
//		if(httpSession.getAttribute("loginedMemberId") == null) {
//			return ResultData.from("F-1", "이미 로그아웃 상태입니다.");
//		}
//		
//		int loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
//		Member loginedMember = memberService.getMemberById(loginedMemberId);
//		
//		httpSession.removeAttribute("loginedMemberId");
//		httpSession.removeAttribute("loginedMember");
//		
//		return ResultData.from("S-1", Ut.f("%s님 로그아웃 성공", loginedMember.getNickname()));
//	}
//
//}


//@Controller
//public class UsrMemberController {
//
//	@Autowired
//	private MemberService memberService;
//
//	@RequestMapping("/usr/member/doJoin")
//	@ResponseBody
//	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
//		
//		int memberCheck = 0;
//		
//		if(Ut.isEmptyOrNull(loginId)) return ResultData.from("F-5", "loginId를 입력해주세요.", "아이디 미입력");
//		else {
//			memberCheck = memberService.getMemberByLoginId(loginId);
//			if (memberCheck==-1) return ResultData.from("F-12", Ut.f("이미 사용중인 loginId(%s)입니다", loginId));
//			//"이미 사용중인 아이디입니다. (" + loginId + ")";
//		}
//		
//		if(Ut.isEmptyOrNull(loginPw)) return ResultData.from("F-6", "loginPw를 입력해주세요.");
//		if(Ut.isEmptyOrNull(name)) return ResultData.from("F-7", "name을 입력해주세요.");
//		if(Ut.isEmptyOrNull(nickname)) return ResultData.from("F-8", "nickname을 입력해주세요.");
//		if(Ut.isEmptyOrNull(cellphoneNum)) return ResultData.from("F-9", "cellphoneNum을 입력해주세요.");
//		if(Ut.isEmptyOrNull(email)) return ResultData.from("F-10", "email을 입력해주세요.");
//		if(!email.contains("@")) return ResultData.from("F-11", "올바른 email을 입력하세요.", email);
//		
//		memberCheck = memberService.getMemberByNameAndEmail(name, email);
//		if (memberCheck==-1) return ResultData.from("F-13", Ut.f("해당 이름(%s)과 이메일(%s)로 이미 가입된 내역이 있습니다.", name, email));
//		
//		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
//		Member member = memberService.getMemberById(id);
//		return ResultData.from("S-6", Ut.f("회원가입에 성공하였습니다. %s님 환영합니다.", nickname), member);
//	}
//
//}

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

