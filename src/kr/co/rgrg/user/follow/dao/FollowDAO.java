package kr.co.rgrg.user.follow.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
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
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		list = ss.selectList("kr.co.rgrg.user.follow.selectFollower", id);
		ss.close();
		
		return list;
	}//selectFollower
	
	/**
	 * 팔로잉 목록을 가져오는 일
	 * @param id
	 * @return
	 */
	public List<FollowingDomain> selectFollowing(String id){
		List<FollowingDomain> list = null;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		list = ss.selectList("kr.co.rgrg.user.follow.selectFollowing", id);
		ss.close();
		
		return list;
	}//selectFollowing
	
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
	
	public static void main(String[] args) {
		//FollowVO fVO = new FollowVO();
		//fVO.setId("guest2");
		//fVO.setFollowing_id("guest1");
		//System.out.println(FollowDAO.getInstance().deleteFollow(fVO));
		System.out.println(FollowDAO.getInstance().selectFollowing("guest2"));
		
	}//Test Main
	
}//FollowDAO
