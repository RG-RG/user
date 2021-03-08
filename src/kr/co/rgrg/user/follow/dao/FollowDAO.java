package kr.co.rgrg.user.follow.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
import kr.co.rgrg.user.follow.domain.FollowDomain;
import kr.co.rgrg.user.follow.vo.FollowVO;
import kr.co.rgrg.user.pagination.RangeVO;

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
	 * @param rVO
	 * @return
	 */
	public List<FollowDomain> selectFollower(RangeVO rVO){
		List<FollowDomain> list = null;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		list = ss.selectList("kr.co.rgrg.user.follow.selectFollower", rVO);
		ss.close();
		
		return list;
	}//selectFollower
	
	/**
	 * �ȷ��� ����� �������� ��
	 * @param rVO
	 * @return
	 */
	public List<FollowDomain> selectFollowing(RangeVO rVO){
		List<FollowDomain> list = null;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		list = ss.selectList("kr.co.rgrg.user.follow.selectFollowing", rVO);
		ss.close();
		
		return list;
	}//selectFollowing
	
	/**
	 * �ȷο� ���¸� Ȯ���ϴ� ��
	 * @param fVO
	 * @return
	 */
	public String selectFollowState(FollowVO fVO) {
		String id = "";

		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		id = ss.selectOne("kr.co.rgrg.user.follow.selectFollowState", fVO);
		ss.close();
		
		return id;
	}//selectFollowState
	
	/**
	 * �ȷο츦 �ϴ� ��
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
	 * ���ȷο츦 �ϴ� ��
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
