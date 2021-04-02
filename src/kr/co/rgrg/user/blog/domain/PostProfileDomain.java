package kr.co.rgrg.user.blog.domain;

public class PostProfileDomain {
	private String id, profile_img, nickname, statement_msg, github, website, visible_email;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getVisible_email() {
		return visible_email;
	}

	public void setVisible_email(String visible_email) {
		this.visible_email = visible_email;
	}
}//class
