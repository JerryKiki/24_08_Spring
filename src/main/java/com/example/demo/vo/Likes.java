package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
	private int id;
	private String regDate;
	private int memberId;
	private int relTypeCode;
	private int relId;
}
