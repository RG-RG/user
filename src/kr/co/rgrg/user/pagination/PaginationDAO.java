package kr.co.rgrg.user.pagination;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;

public class PaginationDAO {
	
	private static PaginationDAO pDAO;
	
	private PaginationDAO() {
	}//PaginationDAO
	
	public static PaginationDAO getInstance() {
		if(pDAO == null) {
			pDAO = new PaginationDAO();
		}
		return pDAO;
	}//getInstance
	
	public int selectTotalCnt(TotalCntVO tcVO) {
		int cnt=0;
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		cnt=ss.selectOne("selectTotalCnt", tcVO);
		ss.close();
		
		return cnt;
	}//selectTotalCnt
	
	public int selectTotalFollowCnt(TotalFollowCntVO tfcVO) {
		int cnt=0;
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		cnt=ss.selectOne("selectTotalFollowCnt", tfcVO);
		ss.close();
		
		return cnt;
	}//selectTotalFollowCnt
	
	public int selectTotalPostCnt(PostRangeVO prVO) {
		int cnt=0;
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		if("tag".equals(prVO.getColumn_name())) {
			cnt=ss.selectOne("selectTotalPostTagSearchCnt", prVO);
		}else {
			cnt=ss.selectOne("selectTotalPostCnt", prVO);
		}//end else
		ss.close();
		
		return cnt;
	}//selectTotalPostCnt
	
	public int selectMainTotalCnt(RangeVO rVO) {
		int cnt=0;
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		cnt=ss.selectOne("selectMainTotalCnt", rVO);
		ss.close();
		
		return cnt;
	}//selectTotalPostCnt
	
	public int selectTotalLikeCnt(TotalCntVO tcVO) {
		int cnt=0;
		
		SqlSession ss=GetRgrgHandler.getInstance().getSqlSession();
		cnt=ss.selectOne("selectLikeCnt", tcVO);
		ss.close();
		
		return cnt;
	}//selectTotalLikeCnt
	
	
}//class
