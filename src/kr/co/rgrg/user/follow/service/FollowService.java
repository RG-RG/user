package kr.co.rgrg.user.follow.service;

import java.util.List;

import kr.co.rgrg.user.follow.domain.FollowerDomain;
import kr.co.rgrg.user.follow.domain.FollowingDomain;
import kr.co.rgrg.user.follow.vo.FollowVO;

public class FollowService {

	/**
	 * 팔로워 목록을 가져오는 일
	 * @param id
	 * @return
	 */
	public List<FollowerDomain> getFollowerList(String id){
		List<FollowerDomain> list = null;
		return list;
	}//getFollowerList
	
	/**
	 * 팔로잉 목록을 가져오는 일
	 * @param id
	 * @return
	 */
	public List<FollowingDomain> getFollowingList(String id){
		List<FollowingDomain> list = null;
		return list;
	}//getFollowingList
	
	/**
	 * 팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public boolean follow(FollowVO fVO) {
		boolean flag = false;
		return flag;
	}//follow
	
	/**
	 * 언팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public boolean unfollow(FollowVO fVO) {
		boolean flag = false;
		return flag;
	}//unfollow
	
}//FollowService
