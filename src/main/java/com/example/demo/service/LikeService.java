package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LikeRepository;
import com.example.demo.vo.Likes;
import com.example.demo.vo.ResultData;

@Service
public class LikeService {
	
	@Autowired
	private LikeRepository likeRepository;

	public List<Likes> getHistoryByMemberId(int loginedMemberId) {
		return likeRepository.getArticleActionHistoryByMemberId(loginedMemberId);
	}

	public boolean checkHistoryByArticleId(List<Likes> likeHistory, int ArticleId) {
		for(Likes likeRow : likeHistory) {
			if(likeRow.getRelId() == ArticleId) {
				return true;
			}
		}
		return false;
	}

	//좋아요 -> 좋아요, 싫어요 -> 싫어요 혹은 최초 반응
	public void updateNoneToggleLikeHistory(int articleId, int loginedMemberId, boolean alreadyActioned, int point) {
		if(alreadyActioned) { //이미 액션 했다면 해제
			//해당 유저의 해당 게시글에 대한 기록 삭제
			likeRepository.removeLikeHistoryOfArticle(articleId, loginedMemberId);
		} else { //좋아요 기록이 없다면 등록
			//해당 유저의 해당 게시글에 대한 기록 추가
			likeRepository.addLikeHistoryOfArticle(articleId, loginedMemberId, point);
		}
	}
	
	//좋아요 -> 싫어요, 싫어요 -> 좋아요
	public void updateToggleLikeHistory(int articleId, int loginedMemberId, int point) {
		//해당 유저의 해당 게시글에 대한 기록 갱신
		likeRepository.updateLikeHistoryOfArticle(articleId, loginedMemberId, point);
	}

	public Likes getHistoryByIds(int loginedMemberId, int id) {
		return likeRepository.getArticleActionHistoryByIds(loginedMemberId, id);
	}

}
