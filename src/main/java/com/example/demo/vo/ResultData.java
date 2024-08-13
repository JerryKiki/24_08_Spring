package com.example.demo.vo;

import lombok.Getter;

//Controller의 리턴 타입이 모두 달라서 도입...
//from이라는 메서드가 실행됐을 때 어떻게 되는지, 어떻게 성공/실패를 판별하는지...
public class ResultData<DT> {

	@Getter
	private String ResultCode;
	@Getter
	private String msg;
	@Getter
	private DT data1;

	public static <DT> ResultData<DT> from(String ResultCode, String msg) {
		return from(ResultCode, msg, null);
	}

	public static <DT> ResultData<DT> from(String ResultCode, String msg, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.ResultCode = ResultCode;
		rd.msg = msg;
		rd.data1 = data1;

		return rd;
	}

	public boolean isSuccess() {
		return ResultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}

	public static <DT> ResultData<DT> newData(ResultData rd, DT newData) {
		return from(rd.getResultCode(), rd.getMsg(), newData);
	}

}



//@Data
//public class ResultData {
//
//	@Getter
//	private String ResultCode;
//	@Getter
//	private String msg;
//	@Getter
//	private Object data1;
//	
//	
//	public static ResultData from(String ResultCode, String msg) {
//		return from(ResultCode, msg, null);
//	}
//	
//
//	public static ResultData from(String ResultCode, String msg, Object data1) {
//		ResultData rd = new ResultData();
//		rd.ResultCode = ResultCode;
//		rd.msg = msg;
//		rd.data1 = data1;
//		
//		return rd;
//	}
//	
//	//이거 관련 변수 필드에 만든 적 없는데 보이는 이유:
//	//boolean형의 is함수(getter느낌)를 만들때의 규칙이 is+대문자...
//	//==> lombok에 의해 저절로 필드에도 있는 변수가 되어버림
//	public boolean isSuccess() {
//		return ResultCode.startsWith("S-");
//	}
//	
//	public boolean isFail() {
//		return !isSuccess();
//	}
//
//}