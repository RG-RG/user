package kr.co.rgrg.user.post.vo;

import java.util.List;

public class PostVO {
	private int post_num;
	private String thumnail, post_title, post_content, publish_flag, hidden_flag, id;
	private String[] tags;
	
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public String getThumnail() {
		return thumnail;
	}
	public void setThumnail(String thumnail) {
		this.thumnail = thumnail;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getPublish_flag() {
		return publish_flag;
	}
	public void setPublish_flag(String publish_flag) {
		this.publish_flag = publish_flag;
	}
	public String getHidden_flag() {
		return hidden_flag;
	}
	public void setHidden_flag(String hidden_flag) {
		this.hidden_flag = hidden_flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
}
