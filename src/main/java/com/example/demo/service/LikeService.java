package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LikeRepository;
import com.example.demo.vo.Likes;

@Service
public class LikeService {
	
	@Autowired
	private LikeRepository likeRepository;

	public List<Likes> getHistoryByMemberId(int loginedMemberId) {
		return likeRepository.getHistoryByMemberId(loginedMemberId);
	}

	public boolean checkHistoryByArticleId(List<Likes> likeHistory, int ArticleId) {
		for(Likes likeRow : likeHistory) {
			if(likeRow.getRelId() == ArticleId) {
				return true;
			}
		}
		return false;
	}

	public void updateHistory(int articleId, int loginedMemberId, boolean alreadyLiked) {
		if(alreadyLiked) { //이미 좋아요 했다면 해제
			//해당 유저의 해당 게시글에 대한 좋아요 기록 삭제
			likeRepository.removeLikeHistoryOfArticle(articleId, loginedMemberId);
		} else { //좋아요 기록이 없다면 등록
			//해당 유저의 해당 게시글에 대한 좋아요 기록 추가
			likeRepository.addLikeHistoryOfArticle(articleId, loginedMemberId);
		}
	}

}
