package kr.co.rgrg.user.follow.service;

import java.util.List;

import kr.co.rgrg.user.follow.dao.FollowDAO;
import kr.co.rgrg.user.follow.domain.FollowDomain;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.RangeVO;

public class FollowService {

	/**
	 * �ȷο� ����� �������� ��
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
	 * �ȷ��� ����� �������� ��
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
	 * �ȷο� ���¸� Ȯ���ϴ� ��
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
	 * �ȷο츦 �ϴ� ��
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
	 * ���ȷο츦 �ϴ� ��
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
