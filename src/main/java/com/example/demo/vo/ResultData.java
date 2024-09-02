package com.example.demo.vo;

import java.util.Map;

import com.example.demo.util.Ut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultData<DT> {

	private String ResultCode;
	private String msg;
	private DT data1;
	private String data1Name;
	private Object data2;
	private String data2Name;

	private Map<String, Object> body;

	public ResultData(String ResultCode, String msg, Object... args) {
		this.ResultCode = ResultCode;
		this.msg = msg;
		this.body = Ut.mapOf(args);
	}

	public static <DT> ResultData<DT> from(String ResultCode, String msg) {
		return from(ResultCode, msg, null, null);
	}

	public static <DT> ResultData<DT> from(String ResultCode, String msg, String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.ResultCode = ResultCode;
		rd.msg = msg;
		rd.data1 = data1;
		rd.data1Name = data1Name;

		return rd;
	}

	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1, String data2Name,
			DT data2) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.ResultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		rd.data2Name = data2Name;
		rd.data2 = data2;

		return rd;
	}

	public boolean isSuccess() {
		return ResultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}

	public static <DT> ResultData<DT> newData(ResultData rd, String dataName, DT newData) {
		return from(rd.getResultCode(), rd.getMsg(), dataName, newData);
	}

	public void setData2(String data2Name, Object data2) {
		this.data2 = data2;
		this.data2Name = data2Name;
	}

}

//Controller의 리턴 타입이 모두 달라서 도입...
//from이라는 메서드가 실행됐을 때 어떻게 되는지, 어떻게 성공/실패를 판별하는지...

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