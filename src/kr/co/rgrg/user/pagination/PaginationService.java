package kr.co.rgrg.user.pagination;

public class PaginationService {
	
	/**
	 * ���̺��, �÷���, �÷����� �����ϴ� TotalCntVO�� �Ű������� �޾� ���ǿ� �´� ���� ���� ���ϴ� method
	 * tcVO �� ���̺���� �ݵ�� ���� ������ �־���ϰ�, �÷���� �÷����� null�̾ �ȴ�.
	 * @param tcVO
	 * @return
	 */
	public int getTotalCnt(TotalCntVO tcVO) {
		int cnt=0;
		
		cnt=PaginationDAO.getInstance().selectTotalCnt(tcVO);
		
		return cnt;
	}//getTotalCnt
	
	/**
	 * ���̺��, �÷���, �÷����� �����ϴ� TotalCntVO�� �Ű������� �޾� ���ǿ� �´� ���� ���� ���ϴ� method
	 * tcVO �� ���̺���� �ݵ�� ���� ������ �־���ϰ�, �÷���� �÷����� null�̾ �ȴ�.
	 * @param tfcVO
	 * @return
	 */
	public int getTotalFollowCnt(TotalFollowCntVO tfcVO) {
		int cnt=0;
		
		cnt=PaginationDAO.getInstance().selectTotalFollowCnt(tfcVO);
		
		return cnt;
	}//getTotalFollowCnt
	
	
	
	/**
	 * ������ total count�� ���ϴ� method
	 * @param rVO
	 * @return
	 */
	public int getMainTotalCnt(RangeVO rVO) {
		int cnt = 0;
		cnt = PaginationDAO.getInstance().selectMainTotalCnt(rVO);
		return cnt;
	}
	
	/**
	 * ���̺��, �÷���, �÷����� �����ϴ� TotalCntVO�� �Ű������� �޾� ���ǿ� �´� ���� ���� ���ϴ� method
	 * tcVO �� ���̺���� �ݵ�� ���� ������ �־���ϰ�, �÷���� �÷����� null�̾ �ȴ�.
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
	 * �� ���������� ������ �Խñ��� �� (10��)
	 * @return
	 */
	public int pageScale() {
		int page_scale=3;
		
		return page_scale;
	}//pageScale
	
	/**
	 * ���� �������� �޾� �Խñ��� ���۹�ȣ�� ���ϴ� method
	 * @param currentPage
	 * @return
	 */
	public int startNum( int current_page ) {
		int page_scale=pageScale();
		int start_num=(current_page-1)*page_scale+1 ;
		return start_num;
	}//startNum
	
	/**
	 * ���� �������� �޾� �Խñ��� ����ȣ�� ���ϴ� method
	 * @param currentPage
	 * @return
	 */
	public int  endNum( int current_page ) {
		int page_scale=pageScale();
		int end_num=((current_page-1)*page_scale+1)+page_scale-1;
		return end_num;
	}//startNum
	
}//class
