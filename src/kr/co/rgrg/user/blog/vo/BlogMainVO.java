package kr.co.rgrg.user.blog.vo;

public class BlogMainVO {
	private String id;
	private boolean hidden_flag; //hidden_flag=true �̸� ���ܾ� �� (�⺻�� false)

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
