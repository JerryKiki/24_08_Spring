package com.example.demo.util;

import java.lang.reflect.Array;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;

import jakarta.servlet.http.HttpSession;

public class Ut {
	
	public static String jsReplace(String resultCode, String msg, String replaceUri) {

		if (resultCode == null) {
			resultCode = "";
		}
		if (msg == null) {
			msg = "";
		}
		if (replaceUri == null) {
			replaceUri = "/";
		}

		String resultMsg = resultCode + "/" + msg;

		return Ut.f("""
					<script>
						let resultMsg = '%s'.trim();

						if(resultMsg.length > 0){
							alert(resultMsg);
						}
						location.replace('%s');
					</script>
				""", resultMsg, replaceUri);
	}

	public static String jsHistoryBack(String resultCode, String msg) {
		if (resultCode == null) {
			resultCode = "";
		}
		if (msg == null) {
			msg = "";
		}

		String resultMsg = resultCode + "/" + msg;

		return Ut.f("""
					<script>
						let resultMsg = '%s'.trim();

						if(resultMsg.length > 0){
							alert(resultMsg);
						}
						history.back();
					</script>
				""", resultMsg);
	}
	
	
	public static boolean isEmptyOrNull(String str) {
			return str==null || str.trim().length() == 0;
		}
		
	public static boolean isEmpty(Object obj) {
			
		if(obj==null) return true;
			
		if(obj instanceof String) {
			return ((String) obj).trim().length() == 0;
		}
			
		if(obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		}
			
		if(obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
			
		return false;
	}
		
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}
		
	public static boolean loginCheck(HttpSession httpSession) {
		if(httpSession.getAttribute("loginedMemberId") != null) {
			return false;
		}
		return true;
	}
}
