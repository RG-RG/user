package kr.co.rgrg.user.pagination;

public class TotalFollowCntVO {
	private String table_name, column_name, column_value, column_name2, column_value2;

	public TotalFollowCntVO() {
	}
	
	public TotalFollowCntVO(String table_name) {
		this.table_name = table_name;
	}
	
	public TotalFollowCntVO(String table_name, String column_name, String column_value, String column_name2, String column_value2) {
		this.table_name = table_name;
		this.column_name = column_name;
		this.column_value = column_value;
		this.column_name2 = column_name2;
		this.column_value2 = column_value2;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
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
	
}//class
