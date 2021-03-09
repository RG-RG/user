package kr.co.rgrg.user.blog.domain;

public class PostProfileDomain {
	private String profile_img, nickname, statement_msg, visible_email, github, website;

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

}//class
