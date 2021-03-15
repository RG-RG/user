package kr.co.rgrg.user.blog.vo;

public class BlogMainVO {
	private String id;
	private Boolean hidden_flag; //hidden_flag=true 이면 숨겨야 함 (기본값 false)

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getHidden_flag() {
		return hidden_flag;
	}

	public void setHidden_flag(Boolean hidden_flag) {
		this.hidden_flag = hidden_flag;
	}

}//class
