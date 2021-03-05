package kr.co.rgrg.user.blog.domain;

import java.util.List;

public class PostDomain {
	
	private String thumbnail, post_title, post_content, input_date, hidden_flag;
	private int view_cnt, comment_cnt;
	private List<String> tag_name;
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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
	public String getInput_date() {
		return input_date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	public String getHidden_flag() {
		return hidden_flag;
	}
	public void setHidden_flag(String hidden_flag) {
		this.hidden_flag = hidden_flag;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public int getComment_cnt() {
		return comment_cnt;
	}
	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}
	public List<String> getTag_name() {
		return tag_name;
	}
	public void setTag_name(List<String> tag_name) {
		this.tag_name = tag_name;
	}
	
}//class
