package kr.co.rgrg.user.follow.dao;

import java.util.List;

import kr.co.rgrg.user.follow.domain.FollowerDomain;
import kr.co.rgrg.user.follow.domain.FollowingDomain;
import kr.co.rgrg.user.follow.vo.FollowVO;

public class FollowDAO {

	private static FollowDAO fDAO;
	
	private FollowDAO() {
	}//FollowDAO
	
	public static FollowDAO getInstance() {
		if (fDAO == null) {
			fDAO = new FollowDAO();
		}//end if
		return fDAO;
	}//getInstance
	
	/**
	 * 팔로워 목록을 가져오는 일
	 * @param id
	 * @return
	 */
	public List<FollowerDomain> selectFollower(String id){
		List<FollowerDomain> list = null;
		return list;
	}//selectFollower
	
	/**
	 * 팔로잉 목록을 가져오는 일
	 * @param id
	 * @return
	 */
	public List<FollowingDomain> selectFollowing(String id){
		List<FollowingDomain> list = null;
		return list;
	}//selectFollowing
	
	/**
	 * 팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public int insertFollow(FollowVO fVO) {
		int cnt = 0;
		return cnt;
	}//insertFollow
	
	/**
	 * 언팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public int deleteFollow(FollowVO fVO) {
		int cnt = 0;
		return cnt;
	}//deleteFollow
	
}//FollowDAO
