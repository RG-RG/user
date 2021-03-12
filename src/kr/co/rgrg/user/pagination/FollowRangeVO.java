package kr.co.rgrg.user.pagination;

public class FollowRangeVO {
	
	private String column_name, column_value, column_name2, column_value2;
	private int start_num, end_num;
	
	public FollowRangeVO() {
	}
	
	/**
	 * 현재페이지를 받아 해당 페이지에서 보여줄 게시글의 시작번호와 끝번호를 구하는 method
	 * @param current_page
	 */
	public FollowRangeVO(int current_page) {
		PaginationService pService=new PaginationService();
		this.start_num=pService.startNum(current_page);
		this.end_num=pService.endNum(current_page);
	}
	
	/**
	 * 현재페이지, 조건의 컬럼명, 값을 받아 값을 초기화하는 method
	 * @param current_page
	 * @param column_name
	 * @param column_value
	 * @param column_name2
	 * @param column_value2
	 */
	public FollowRangeVO(int current_page, String column_name, String column_value, String column_name2, String column_value2) {
		PaginationService pService=new PaginationService();
		this.start_num=pService.startNum(current_page);
		this.end_num=pService.endNum(current_page);
		this.column_name = column_name;
		this.column_value = column_value;
		this.column_name2 = column_name2;
		this.column_value2 = column_value2;
	}
	
	public FollowRangeVO(String column_name, String column_value, String column_name2, String column_value2, int start_num, int end_num) {
		this.column_name = column_name;
		this.column_value = column_value;
		this.column_name2 = column_name2;
		this.column_value2 = column_value2;
		this.start_num = start_num;
		this.end_num = end_num;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getColumn_value() {
		return column_value;
	}

	public void setColumn_value(String column_value) {
		this.column_value = column_value;
	}
	
	public String getColumn_name2() {
		return column_name2;
	}
	
	public void setColumn_name2(String column_name2) {
		this.column_name2 = column_name2;
	}
	
	public String getColumn_value2() {
		return column_value2;
	}
	
	public void setColumn_value2(String column_value2) {
		this.column_value2 = column_value2;
	}

	public int getStart_num() {
		return start_num;
	}

	public void setStart_num(int start_num) {
		this.start_num = start_num;
	}

	public int getEnd_num() {
		return end_num;
	}

	public void setEnd_num(int end_num) {
		this.end_num = end_num;
	}
	
}//class
