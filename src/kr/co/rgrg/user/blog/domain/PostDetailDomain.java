package kr.co.rgrg.user.blog.domain;

import java.util.List;

public class PostDetailDomain {
	private String id, nickname, post_title, input_date, post_content, hidden_flag;
	private int like_cnt, comment_cnt;
	private List<String> tag_name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getInput_date() {
		return input_date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getHidden_flag() {
		return hidden_flag;
	}
	public void setHidden_flag(String hidden_flag) {
		this.hidden_flag = hidden_flag;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
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
	@Override
	public String toString() {
		return "PostDetailDomain [id=" + id + ", nickname=" + nickname + ", post_title=" + post_title + ", input_date="
				+ input_date + ", post_content=" + post_content + ", hidden_flag=" + hidden_flag + ", like_cnt="
				+ like_cnt + ", comment_cnt=" + comment_cnt + ", tag_name=" + tag_name + "]";
	}
	
}//class
