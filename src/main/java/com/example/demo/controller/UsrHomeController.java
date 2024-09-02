package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.MemberService;
import com.example.demo.util.crawlTest;
import com.example.demo.vo.Member;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrHomeController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/home/main")
	public String showMain(HttpSession httpSession, Model model) {
		
		if(httpSession.getAttribute("loginedMemberId") != null) {
			int loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
			Member loginedMember = memberService.getMemberById(loginedMemberId);
			model.addAttribute("isLogined", true);
			model.addAttribute("loginedMember", loginedMember);
		}
		
		return "/usr/home/main"; //"WEB-INF/jsp/usr/home/main.jsp"에서 prefix, suffix 생략(yml에 설정)
	}
	
	//이건 리스폰스바디가 없어야됨
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("/usr/crawl")
	public String doCrawl() {

		crawlTest.crawl();

		return "redirect:/usr/home/main";
	}
	
}
	
//	static int num;
//	static double dNum;
//	static String str;
//	static boolean bool;
//	
//	static {
//		num = 0;
//		dNum = 0;
//		str = "";
//		bool = false;
//	}
//	
//	//일반 테스트	
//	@RequestMapping("/usr/home/main")
//	@ResponseBody
//	public String showMain() {
//		return "안녕하세요";
//	}
//	
//	@RequestMapping("/usr/home/main2")
//	@ResponseBody
//	public String showMain2() {
//		return "잘가";
//	}
//	
//	@RequestMapping("/usr/home/main3")
//	@ResponseBody
//	public int showMain3() {
//		return 1+2;
//	}
//	
//	@RequestMapping("/usr/home/getNum")
//	@ResponseBody
//	public int getNum() {
//		return num++;
//	}
//	
//	@RequestMapping("/usr/home/resetNum")
//	@ResponseBody
//	public String resetNum() {
//		num = 0;
//		return "num값 0으로 초기화";
//	}
//	
//	@RequestMapping("/usr/home/resetNumValue")
//	@ResponseBody
//	public String resetNumValue(int value) {
//		//인자는 jsp때처럼 ?value= 로 주면 된다
//		//parameter 이름은 value여야함 ==> 받기로한 인자의 이름이 value니깐
//		num = value;
//		return "num값 " + value + "(으)로 초기화";
//	}
//	
//	//기본변수 + String 테스트
//	@RequestMapping("/usr/home/getInt")
//	@ResponseBody
//	public int getInt() {
//		return 1;
//	}
//	
//	@RequestMapping("/usr/home/getString")
//	@ResponseBody
//	public String getString(String name) {
//		return name + "님 안녕하세요";
//		//이건 인자 안넣어도 null로 나옴
//	}
//	
//	
//	@RequestMapping("/usr/home/getDouble")
//	@ResponseBody
//	public double getDouble() {
//		
//		return dNum += 0.1;
//		
//		//return dNum++;
//		//정수만 이용할때는 n.0으로 표기됨
//	}
//	
//	@RequestMapping("/usr/home/getBoolean")
//	@ResponseBody
//	public boolean getBooelan() {
//		
//		bool = !bool;
//		
//		return bool;
//	}
//	
//	//Map, List, Article(생성한 클래스)
//	@RequestMapping("/usr/home/getMap")
//	@ResponseBody
//	public Map<Integer, String> getMap() { //int key, String fruit
//		
//		Map<Integer, String> map = new HashMap<Integer, String>();
//		
//		map.put(1, "사과");
//		map.put(2, "바나나");
//		map.put(3, "포도");
//		//map.put(key, fruit);
//		
//		return map;
//		//객체 주소가 아닌 안에 들어있는 내용 자체가 나온다 {1: 사과, ...}
//		//인자 있으면 안넣으면 안나옴, 타입 mismatch여도 안나옴
//	}
//	
//	@RequestMapping("/usr/home/getList")
//	@ResponseBody
//	public List<String> getList() { 
//		
//		List<String> list = new ArrayList<String>();
//		
//		list.add("사과");
//		list.add("바나나");
//		list.add("포도");
//		
//		return list;
//		//이 또한 객체 주소가 아닌 안에 들어있는 내용 자체가 나온다 [사과, ...]
//	}
	
	
//	@RequestMapping("/usr/home/getArticle")
//	@ResponseBody
//	public Article getArticle() {
//		
//		Article article = new Article(1, "제목1", "내용1");
//		
//		return article;
//		//어래 내가 만든 클래스는 왜 안됨? ==> HttpMediaTypeNotAcceptableException
//		//==> 클래스 안에 getter를 만들어주니까 된다!
//		//==> @Data/@AllArgsConstructor/@NoArgsConstructor 붙여주기
//		//==> 맵처럼 객체모양 그대로가 나옴
//	}

//테스트용 클래스
//class Article {
//	
//	int id;
//	String title;
//	String body;
//	
//	public Article(int id, String title, String body) {
//		this.id = id;
//		this.title = title;
//		this.body = body;
//	}
//	
//	public int getId() {
//		return id;
//	}
//	
//	public String getTitle() {
//		return title;
//	}
//	
//	public String getBody() {
//		return body;
//	}
//}