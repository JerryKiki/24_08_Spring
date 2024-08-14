package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.repository.MemberRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.LoginSession;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public ResultData<Integer> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		Member existsMember = getMemberByLoginId(loginId);

		if (existsMember != null) {
			return ResultData.from("F-7", Ut.f("이미 사용중인 아이디(%s)입니다.", loginId));
		}

		existsMember = getMemberByNameAndEmail(name, email);

		if (existsMember != null) {
			return ResultData.from("F-8", Ut.f("이미 사용중인 이름(%s)과 이메일(%s)입니다.", name, email));
		}

		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);

		int id = memberRepository.getLastInsertId();

		return ResultData.from("S-1", "회원가입 성공", "가입된 회원 번호", id);
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
}

//@Service
//public class MemberService {
//
//	@Autowired
//	private MemberRepository memberRepository;
//
//	public MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}
//
//	public int doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
//		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
//		return memberRepository.getLastInsertId();
//	}
//
//	public Member getMemberById(int id) {
//		return memberRepository.getMemberById(id);
//	}
//	
//	public int getMemberByLoginId(String loginId) {
//		
//		Member memberCheck = memberRepository.getMemberByLoginId(loginId);
//		if (memberCheck!=null) return -1;
//		
//		else return 0;
//	}
//	
//	public int getMemberByNameAndEmail(String name, String Email) {
//		
//		Member memberCheck = memberRepository.getMemberByNameAndEmail(name, Email);
//		if (memberCheck!=null) return -1;
//		
//		else return 0;
//	}
//
//}

//	@Autowired
//	private ArticleRepository articleRepositoy;
//	
//	ArticleService(ArticleRepository articleRepositoy) {
//		this.articleRepositoy = articleRepositoy;
//		//생성과 동시에 테스트 데이터 생성
//		makeTestData();
//	}
//	
//	public void makeTestData() {
//
//		for(int i = 0; i < 10; i++) {
//			int id = lastArticleId + 1;
//			String title = String.format("제목%d", id);
//			String body = String.format("내용%d", id);
//			Article article = new Article(id, title, body);
//			articles.add(article);
//			lastArticleId++;
//		}
//	}
//	
//	public Article getArticlebyId(int id) {
//		
//		Article article = null;
//		
//		for(int i = 0; i < this.articles.size(); i++) {
//			if(articles.get(i).getId() == id) {
//				article = articles.get(i);
//			}
//		}
//		
//		return article;
//	}
//	
//	public String writeArticle(String title, String body) {
//		int id = lastArticleId + 1;
//		Article article = new Article(id, title, body);
//		articles.add(article);
//		lastArticleId++;
//		return String.format("%d번 게시글 생성됨", id);
//	}
//	
//	public Object showList() {
//		return articles;
//	}
//	
//	public String deleteArticle(int id) {
//		
//		String rs = "";
//		
//		Article article = getArticlebyId(id);
//		
//		if (article == null) {
//			rs = String.format("%d번 게시글은 없습니다.", id);
//		} else {
//			articles.remove(article);
//			rs = String.format("%d번 글이 삭제되었습니다.", id);
//		}
//		
//		return rs;
//	}
//	
//	public String updateArticle(int id, String title, String body) {
//		
//		if (title == null) return "title을 입력하세요.";
//		if (body == null) return "body를 입력하세요.";
//		
//		Article article = getArticlebyId(id);
//		
//		if (article == null) {
//			return String.format("%d번 게시글은 없습니다.", id);
//		} else {
//			article.setTitle(title);
//			article.setBody(body);
//			
//			String updateInfo = article.toString();
//			
//			//리턴 타입을 Object로 하면 toString을 쓰지 않고도 바로 +만 해서 리턴할 수 있다
//			return String.format("%d번 글이 수정되었습니다.<br>", id) + "수정 결과 : " + updateInfo;
//		}
//	}

//public ResultData<Integer> doLogin(HttpSession httpSession, String loginId, String loginPw) {
//
//if(httpSession.getAttribute("loginedMemberId") != null) {
//	Member loginedMember = (Member) httpSession.getAttribute("Member");
//	return ResultData.from("F-3", Ut.f("이미 로그인 되어 있습니다. (%s)", loginedMember.getLoginId()));
//}
//
//Member existsMember = getMemberByLoginId(loginId);
//
//if (existsMember == null) {
//	return ResultData.from("F-4", Ut.f("존재하지 않는 아이디(%s)입니다.", loginId));
//}
//
//if(!existsMember.getLoginPw().equals(loginPw)) {
//	return ResultData.from("F-5", "비밀번호가 틀립니다.");
//}
//
//httpSession.setAttribute("loginedMemberId", existsMember.getId());
//httpSession.setAttribute("Member", existsMember);
////LoginSession.setLoginStatus(existsMember, existsMember.getId());
//
//return ResultData.from("S-1", Ut.f("%s님 로그인 성공!", existsMember.getNickname()), existsMember.getId());
//}
//
