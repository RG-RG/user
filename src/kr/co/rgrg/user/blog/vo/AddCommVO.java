package kr.co.rgrg.user.blog.vo;

public class AddCommVO {
	private String id, comm_content;
	private int post_num;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComm_content() {
		return comm_content;
	}
	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	@Override
	public String toString() {
		return "AddCommVO [id=" + id + ", comm_content=" + comm_content + ", post_num=" + post_num + "]";
	}
	
}//class
