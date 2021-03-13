package kr.co.rgrg.user.follow.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
import kr.co.rgrg.user.follow.domain.FollowDomain;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.FollowRangeVO;

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
	 * @param frVO
	 * @return
	 */
	public List<FollowDomain> selectFollower(FollowRangeVO frVO){
		List<FollowDomain> list = null;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		list = ss.selectList("kr.co.rgrg.user.follow.selectFollower", frVO);
		ss.close();
		
		return list;
	}//selectFollower
	
	/**
	 * 팔로잉 목록을 가져오는 일
	 * @param frVO
	 * @return
	 */
	public List<FollowDomain> selectFollowing(FollowRangeVO frVO){
		List<FollowDomain> list = null;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		list = ss.selectList("kr.co.rgrg.user.follow.selectFollowing", frVO);
		ss.close();
		
		return list;
	}//selectFollowing
	
	/**
	 * 팔로워 목록 개수를 세는 일
	 * @param frVO
	 * @return
	 */
	public int selectFollowerCnt(FollowRangeVO frVO) {
		int cnt = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		cnt = ss.selectOne("kr.co.rgrg.user.follow.selectFollowerCnt", frVO);
		ss.close();
		
		return cnt;
	}//selectFollowerCnt
	
	/**
	 * 팔로잉 목록 개수를 세는 일
	 * @param frVO
	 * @return
	 */
	public int selectFollowingCnt(FollowRangeVO frVO) {
		int cnt = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		cnt = ss.selectOne("kr.co.rgrg.user.follow.selectFollowingCnt", frVO);
		ss.close();
		
		return cnt;
	}//selectFollowingCnt
	
	/**
	 * 팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public int insertFollow(FollowVO fVO) {
		int cnt = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		cnt = ss.insert("kr.co.rgrg.user.follow.insertFollow", fVO);
		ss.commit();
		ss.close();
		
		return cnt;
	}//insertFollow
	
	/**
	 * 언팔로우를 하는 일
	 * @param fVO
	 * @return
	 */
	public int deleteFollow(FollowVO fVO) {
		int cnt = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		cnt = ss.delete("kr.co.rgrg.user.follow.deleteFollow", fVO);
		ss.commit();
		ss.close();
		
		return cnt;
	}//deleteFollow
	
}//FollowDAO
