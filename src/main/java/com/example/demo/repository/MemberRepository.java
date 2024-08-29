package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

	public Member getMemberByNameAndEmail(String name, String email);

	@Update("""
			UPDATE `member`
			SET updateDate = NOW(),
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			WHERE id = #{memberId}
			""")
	public int updateInfoExceptPw(int memberId, String name, String nickname, String cellphoneNum, String email);

	@Update("""
			UPDATE `member`
			SET updateDate = NOW(),
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			WHERE id = #{memberId}
			""")
	public int updateInfoWithPw(int memberId, String loginPw, String name, String nickname, String cellphoneNum, String email);

}