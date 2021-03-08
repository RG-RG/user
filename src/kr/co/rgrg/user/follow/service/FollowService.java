package kr.co.rgrg.user.follow.service;

import java.util.List;

import kr.co.rgrg.user.follow.dao.FollowDAO;
import kr.co.rgrg.user.follow.domain.FollowDomain;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.RangeVO;

public class FollowService {

	/**
	 * 팔로워 목록을 가져오는 일
	 * @param rVO
	 * @return
	 */
	public List<FollowDomain> getFollowerList(RangeVO rVO){
		List<FollowDomain> list = null;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		list = fDAO.selectFollower(rVO);
		
		return list;
	}//getFollowerList
	
	/**
	 * 팔로잉 목록을 가져오는 일
	 * @param rVO
	 * @return
	 */
	public List<FollowDomain> getFollowingList(RangeVO rVO){
		List<FollowDomain> list = null;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		list = fDAO.selectFollowing(rVO);
		
		return list;
	}//getFollowingList
	
	/**
	 * 팔로우 상태를 확인하는 일
	 * @param fVO
	 * @return
	 */
	public String chkFollowState(FollowVO fVO) {
		String id = "";
		
		FollowDAO fDAO = FollowDAO.getInstance();
		id = fDAO.selectFollowState(fVO);
		
		return id;
	}//chkFollowState
	
	/**
	 * 팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public boolean follow(FollowVO fVO) {
		boolean flag = false;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		flag = fDAO.insertFollow(fVO) > 0;
		
		return flag;
	}//follow
	
	/**
	 * 언팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public boolean unfollow(FollowVO fVO) {
		boolean flag = false;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		flag = fDAO.deleteFollow(fVO) > 0;
		
		return flag;
	}//unfollow
	
}//FollowService
