package kr.co.rgrg.user.blog.domain;

public class ProfileDomain {
	
	private String profile_img, nickname, statement_msg, visible_email, github, website;;
	private int follower_cnt, following_cnt;
	
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
	
}//class