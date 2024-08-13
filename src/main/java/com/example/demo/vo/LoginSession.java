package com.example.demo.vo;

public class LoginSession {
	private static Member loginedMember;
	private static int loginedMemberId;
	
	static void init() {
		loginedMember = null;
		loginedMemberId = -1;
	}
	
	public static void setLoginStatus(Member lastLoginedMemeber, int lastLoginedMemberId) {
		loginedMember = lastLoginedMemeber;
		loginedMemberId = lastLoginedMemberId;
	}
	
	public static void setLogout() {
		loginedMember = null;
		loginedMemberId = -1;
	}
	
	public static Member getLoginedMember() {
		return loginedMember;
	}
	
	public static int getLoginedMemberId() {
		return loginedMemberId;
	}
}
