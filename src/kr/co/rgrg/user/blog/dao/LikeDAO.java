package kr.co.rgrg.user.blog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.blog.domain.LikeDomain;
import kr.co.rgrg.user.blog.vo.LikePostVO;
import kr.co.rgrg.user.dao.GetRgrgHandler;
import kr.co.rgrg.user.pagination.RangeVO;

public class LikeDAO {
	
	private static LikeDAO lDAO;
	
	private LikeDAO() {
	}//LikeDAO
	
	public static LikeDAO getInstance() {
		if (lDAO==null) {
			lDAO=new LikeDAO();
		}//end if
		return lDAO;
	}//getInstance
	
	public List<LikeDomain> selectLikeList(RangeVO rVO){
		List<LikeDomain> list=null;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		list = ss.selectList("kr.co.rgrg.user.like.selectLikeList",rVO);
		ss.close();
		
		return list;
	}//selectLikeList
	
	public int insertLikePost(LikePostVO lpVO) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		int cnt=ss.insert("kr.co.rgrg.user.like.insertLikePost",lpVO);
		ss.commit();
		ss.close();
		
		return cnt;
	}//insertLikePost
	
	public int deleteLikePost(LikePostVO lpVO) {
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		int cnt=ss.delete("kr.co.rgrg.user.like.deleteLikePost",lpVO);
		ss.commit();
		ss.close();
		
		return cnt;
	}//deleteLikePost
	
}//class
