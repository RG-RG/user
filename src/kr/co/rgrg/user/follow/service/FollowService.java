package kr.co.rgrg.user.follow.service;

import java.util.List;

import kr.co.rgrg.user.follow.domain.FollowerDomain;
import kr.co.rgrg.user.follow.domain.FollowingDomain;
import kr.co.rgrg.user.follow.vo.FollowVO;

public class FollowService {

	/**
	 * �ȷο� ����� �������� ��
	 * @param id
	 * @return
	 */
	public List<FollowerDomain> getFollowerList(String id){
		List<FollowerDomain> list = null;
		return list;
	}//getFollowerList
	
	/**
	 * �ȷ��� ����� �������� ��
	 * @param id
	 * @return
	 */
	public List<FollowingDomain> getFollowingList(String id){
		List<FollowingDomain> list = null;
		return list;
	}//getFollowingList
	
	/**
	 * �ȷο츦 �ϴ� ��
	 * @param fVO
	 * @return
	 */
	public boolean follow(FollowVO fVO) {
		boolean flag = false;
		return flag;
	}//follow
	
	/**
	 * ���ȷο츦 �ϴ� ��
	 * @param fVO
	 * @return
	 */
	public boolean unfollow(FollowVO fVO) {
		boolean flag = false;
		return flag;
	}//unfollow
	
}//FollowService
