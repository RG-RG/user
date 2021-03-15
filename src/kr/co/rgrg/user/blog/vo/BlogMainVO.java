package kr.co.rgrg.user.blog.vo;

public class BlogMainVO {
	private String id, hidden_flag; //hidden_flag="hodden" 이면 숨겨야 함

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHidden_flag() {
		return hidden_flag;
	}

	public void setHidden_flag(String hidden_flag) {
		this.hidden_flag = hidden_flag;
	}
	
}//class
