package kr.co.rgrg.user.pagination;

public class PaginationService {
	
	/**
	 * 테이블명, 컬럼명, 컬럼값을 저장하는 TotalCntVO를 매개변수로 받아 조건에 맞는 행의 수를 구하는 method
	 * tcVO 중 테이블명은 반드시 값을 가지고 있어야하고, 컬럼명과 컬럼값은 null이어도 된다.
	 * @param tcVO
	 * @return
	 */
	public int getTotalCnt(TotalCntVO tcVO) {
		int cnt=0;
		
		cnt=PaginationDAO.getInstance().selectTotalCnt(tcVO);
		
		return cnt;
	}//getTotalCnt
	
	/**
	 * 테이블명, 컬럼명, 컬럼값을 저장하는 TotalCntVO를 매개변수로 받아 조건에 맞는 행의 수를 구하는 method
	 * tcVO 중 테이블명은 반드시 값을 가지고 있어야하고, 컬럼명과 컬럼값은 null이어도 된다.
	 * @param tfcVO
	 * @return
	 */
	public int getTotalFollowCnt(TotalFollowCntVO tfcVO) {
		int cnt=0;
		
		cnt=PaginationDAO.getInstance().selectTotalFollowCnt(tfcVO);
		
		return cnt;
	}//getTotalFollowCnt
	
	
	
	/**
	 * 메인의 total count를 구하는 method
	 * @param rVO
	 * @return
	 */
	public int getMainTotalCnt(RangeVO rVO) {
		int cnt = 0;
		cnt = PaginationDAO.getInstance().selectMainTotalCnt(rVO);
		return cnt;
	}
	
	/**
	 * 테이블명, 컬럼명, 컬럼값을 저장하는 TotalCntVO를 매개변수로 받아 조건에 맞는 행의 수를 구하는 method
	 * tcVO 중 테이블명은 반드시 값을 가지고 있어야하고, 컬럼명과 컬럼값은 null이어도 된다.
	 * @param tfcVO
	 * @return
	 */
	public int getTotalPostCnt(PostRangeVO prVO) {
		int cnt=0;
		
		cnt=PaginationDAO.getInstance().selectTotalPostCnt(prVO);
		
		return cnt;
	}//getTotalFollowCnt
	
	public int getTotalLikeCnt(TotalCntVO tcVO) {
		int cnt=0;
		
		cnt=PaginationDAO.getInstance().selectTotalLikeCnt(tcVO);
		
		return cnt;
	}//getTotalLikeCnt
	
	/**
	 * 한 페이지에서 보여줄 게시글의 수 (10개)
	 * @return
	 */
	public int pageScale() {
		int page_scale=3;
		
		return page_scale;
	}//pageScale
	
	/**
	 * 현재 페이지를 받아 게시글의 시작번호를 구하는 method
	 * @param currentPage
	 * @return
	 */
	public int startNum( int current_page ) {
		int page_scale=pageScale();
		int start_num=(current_page-1)*page_scale+1 ;
		return start_num;
	}//startNum
	
	/**
	 * 현재 페이지를 받아 게시글의 끝번호를 구하는 method
	 * @param currentPage
	 * @return
	 */
	public int  endNum( int current_page ) {
		int page_scale=pageScale();
		int end_num=((current_page-1)*page_scale+1)+page_scale-1;
		return end_num;
	}//startNum
	
}//class
