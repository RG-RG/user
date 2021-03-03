package kr.co.rgrg.user.blog.service;

import java.util.List;

import kr.co.rgrg.user.blog.dao.LikeDAO;
import kr.co.rgrg.user.blog.domain.LikeDomain;
import kr.co.rgrg.user.pagination.RangeVO;

public class LikeService {
	
	public List<LikeDomain> getLikeList(RangeVO rVO){
		List<LikeDomain> list=null;
		
		LikeDAO lDAO=LikeDAO.getInstance();
		list=lDAO.selectLikeList(rVO);
		
		return list;
	}//getLikeList
	
}//class
