package kr.co.rgrg.user.follow.service;

import java.util.List;

import org.json.simple.JSONObject;

import kr.co.rgrg.user.follow.dao.FollowDAO;
import kr.co.rgrg.user.follow.domain.FollowDomain;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.FollowRangeVO;
import kr.co.rgrg.user.pagination.RangeVO;

public class FollowService {

	/**
	 * �ȷο� ����� �������� ��
	 * @param frVO
	 * @return
	 */
	public List<FollowDomain> getFollowerList(FollowRangeVO frVO){
		List<FollowDomain> list = null;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		list = fDAO.selectFollower(frVO);
		
		return list;
	}//getFollowerList
	
	/**
	 * �ȷ��� ����� �������� ��
	 * @param frVO
	 * @return
	 */
	public List<FollowDomain> getFollowingList(FollowRangeVO frVO){
		List<FollowDomain> list = null;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		list = fDAO.selectFollowing(frVO);
		
		return list;
	}//getFollowingList
	
	/**
	 * �ȷο츦 �ϴ� ��
	 * @param fVO
	 * @return
	 */
	public String follow(FollowVO fVO) {
		boolean flag = false;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		flag = fDAO.insertFollow(fVO) > 0;
		JSONObject json = new JSONObject();
		json.put("follow_result", flag);
		
		return json.toJSONString();
	}//follow
	
	/**
	 * ���ȷο츦 �ϴ� ��
	 * @param fVO
	 * @return
	 */
	public String unfollow(FollowVO fVO) {
		boolean flag = false;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		flag = fDAO.deleteFollow(fVO) > 0;
		JSONObject json = new JSONObject();
		json.put("unfollow_result", flag);
		
		return json.toJSONString();
	}//unfollow
	
}//FollowService
