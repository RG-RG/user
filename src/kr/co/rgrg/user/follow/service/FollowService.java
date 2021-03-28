package kr.co.rgrg.user.follow.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.rgrg.user.follow.dao.FollowDAO;
import kr.co.rgrg.user.follow.domain.FollowDomain;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.FollowRangeVO;
import kr.co.rgrg.user.pagination.PaginationDAO;
import kr.co.rgrg.user.pagination.RangeVO;

public class FollowService {

	/**
	 * 팔로워 목록을 가져오는 일
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
	 * 팔로워 목록을 더 가져오는 일
	 * @param frVO
	 * @return
	 */
	public String getMoreFollowerList(FollowRangeVO frVO){
		JSONObject json = new JSONObject();

		List<FollowDomain> list = null;
		FollowDAO fDAO = FollowDAO.getInstance();
		list = fDAO.selectFollower(frVO);
		
		int follower_cnt = FollowDAO.getInstance().selectFollowerCnt(frVO);
		int end_num = frVO.getEnd_num();
		json.put("end", follower_cnt <= end_num);

		JSONArray ja = new JSONArray();
		JSONObject tmp = null;
		for ( FollowDomain fd : list ) {
			tmp = new JSONObject();
			tmp.put("id", fd.getId());
			tmp.put("following_id", fd.getFollowing_id());
			tmp.put("nickname", fd.getNickname());
			tmp.put("statement_msg", fd.getStatement_msg());
			tmp.put("profile_img", fd.getProfile_img());
			ja.add(tmp);
		}//end for
		json.put("follower_list", ja);
		
		return json.toJSONString();
	}//getMoreFollowerList
	
	/**
	 * 팔로잉 목록을 가져오는 일
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
	 * 팔로잉 목록을 더 가져오는 일
	 * @param frVO
	 * @return
	 */
	public String getMoreFollowingList(FollowRangeVO frVO){
		JSONObject json = new JSONObject();

		List<FollowDomain> list = null;
		FollowDAO fDAO = FollowDAO.getInstance();
		list = fDAO.selectFollowing(frVO);
		
		int following_cnt = FollowDAO.getInstance().selectFollowingCnt(frVO);
		int end_num = frVO.getEnd_num();
		json.put("end", following_cnt <= end_num);

		JSONArray ja = new JSONArray();
		JSONObject tmp = null;
		for ( FollowDomain fd : list ) {
			tmp = new JSONObject();
			tmp.put("id", fd.getId());
			tmp.put("following_id", fd.getFollowing_id());
			tmp.put("nickname", fd.getNickname());
			tmp.put("statement_msg", fd.getStatement_msg());
			tmp.put("profile_img", fd.getProfile_img());
			ja.add(tmp);
		}//end for
		json.put("following_list", ja);
		
		return json.toJSONString();
	}//getMoreFollowingList
		
	/**
	 * 팔로워 목록 개수를 세는 일
	 * @param frVO
	 * @return
	 */
	public int getFollowerCnt(FollowRangeVO frVO) {
		int cnt = 0;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		cnt = fDAO.selectFollowerCnt(frVO);
		
		return cnt;
	}//getFollowerCnt
	
	/**
	 * 팔로잉 목록 개수를 세는 일
	 * @param frVO
	 * @return
	 */
	public int getFollowingCnt(FollowRangeVO frVO) {
		int cnt = 0;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		cnt = fDAO.selectFollowingCnt(frVO);
		
		return cnt;
	}//getFollowingCnt
	
	/**
	 * 팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public String follow(FollowVO fVO) {
		boolean flag = false;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		flag = fDAO.insertFollow(fVO) > 0;
		JSONObject json = new JSONObject();
		json.put("result", flag);
		
		return json.toJSONString();
	}//follow
	
	/**
	 * 언팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public String unfollow(FollowVO fVO) {
		boolean flag = false;
		
		FollowDAO fDAO = FollowDAO.getInstance();
		flag = fDAO.deleteFollow(fVO) != 0;
		JSONObject json = new JSONObject();
		json.put("result", flag);
		
		return json.toJSONString();
	}//unfollow
	
}//FollowService
