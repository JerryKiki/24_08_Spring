package com.example.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {

	//tip: 여기서 여러줄로 쓰는 법
	@Select("""
			SELECT
			LAST_INSERT_ID();
			""")
	public int getLastInsertId();

//	@Insert("INSERT INTO `member` SET regDate = NOW(), updateDate = NOW(), loginId = #{loginId}, loginPw = #{loginPw}, `name` = #{name}, nickname = #{nickname}, cellphoneNum = #{cellphoneNum}, email = #{email}")
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

//	@Select("SELECT * FROM `member` WHERE id = #{id}")
	public Member getMemberById(int id);
	
	public Member getMemberByLoginId(String loginId);

}