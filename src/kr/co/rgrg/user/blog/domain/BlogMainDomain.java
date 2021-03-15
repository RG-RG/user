package kr.co.rgrg.user.blog.domain;

import java.util.List;

public class BlogMainDomain {
	private String profile_img, nickname, statement_msg, visible_email, github, website;;
	private int post_cnt, follower_cnt, following_cnt;
	private List<TagDomain> tag_list;
	private List<PostDomain> post_list;
	
	public String getProfile_img() {
		return profile_img;
	}
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getStatement_msg() {
		return statement_msg;
	}
	public void setStatement_msg(String statement_msg) {
		this.statement_msg = statement_msg;
	}
	public String getVisible_email() {
		return visible_email;
	}
	public void setVisible_email(String visible_email) {
		this.visible_email = visible_email;
	}
	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getPost_cnt() {
		return post_cnt;
	}
	public void setPost_cnt(int post_cnt) {
		this.post_cnt = post_cnt;
	}
	public int getFollower_cnt() {
		return follower_cnt;
	}
	public void setFollower_cnt(int follower_cnt) {
		this.follower_cnt = follower_cnt;
	}
	public int getFollowing_cnt() {
		return following_cnt;
	}
	public void setFollowing_cnt(int following_cnt) {
		this.following_cnt = following_cnt;
	}
	public List<TagDomain> getTag_list() {
		return tag_list;
	}
	public void setTag_list(List<TagDomain> tag_list) {
		this.tag_list = tag_list;
	}
	public List<PostDomain> getPost_list() {
		return post_list;
	}
	public void setPost_list(List<PostDomain> post_list) {
		this.post_list = post_list;
	}
	
}//class