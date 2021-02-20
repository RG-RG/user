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
	 * �ȷο� ����� �������� ��
	 * @param id
	 * @return
	 */
	public List<FollowerDomain> selectFollower(String id){
		List<FollowerDomain> list = null;
		return list;
	}//selectFollower
	
	/**
	 * �ȷ��� ����� �������� ��
	 * @param id
	 * @return
	 */
	public List<FollowingDomain> selectFollowing(String id){
		List<FollowingDomain> list = null;
		return list;
	}//selectFollowing
	
	/**
	 * �ȷο츦 �ϴ� ��
	 * @param fVO
	 * @return
	 */
	public int insertFollow(FollowVO fVO) {
		int cnt = 0;
		return cnt;
	}//insertFollow
	
	/**
	 * ���ȷο츦 �ϴ� ��
	 * @param fVO
	 * @return
	 */
	public int deleteFollow(FollowVO fVO) {
		int cnt = 0;
		return cnt;
	}//deleteFollow
	
}//FollowDAO
