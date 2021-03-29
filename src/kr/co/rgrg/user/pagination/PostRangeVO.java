package kr.co.rgrg.user.pagination;

public class PostRangeVO {
	
	private String id, column_name, column_value;
	private int start_num, end_num;
	private boolean hidden_flag; //hidden_flag=true 이면 숨겨야 함 (기본값 false)
	
	public PostRangeVO() {
	}
	
	/**
	 * 현재페이지를 받아 해당 페이지에서 보여줄 게시글의 시작번호와 끝번호를 구하는 method
	 * @param current_page
	 */
	public PostRangeVO(int current_page) {
		PaginationService pService=new PaginationService();
		this.start_num=pService.startNum(current_page);
		this.end_num=pService.endNum(current_page);
	}
	
	/**
	 * 현재페이지, 조건의 컬럼명, 값을 받아 값을 초기화하는 method
	 * @param current_page
	 * @param id
	 * @param column_name
	 * @param column_value
	 */
	public PostRangeVO(int current_page, String id, String column_name, String column_value) {
		PaginationService pService=new PaginationService();
		this.start_num=pService.startNum(current_page);
		this.end_num=pService.endNum(current_page);
		this.id = id;
		this.column_name = column_name;
		this.column_value = column_value;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getHidden_flag() {
		return hidden_flag;
	}

	public void setHidden_flag(boolean hidden_flag) {
		this.hidden_flag = hidden_flag;
	}
	
}//class
