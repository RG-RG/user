package kr.co.rgrg.user.mypage.domain;

public class MypageDomain {
	
	private String nickname, blog_name, alarm_flag, github, website, visible_email, auth_email, profile_img, statement_msg;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBlog_name() {
		return blog_name;
	}

	public void setBlog_name(String blog_name) {
		this.blog_name = blog_name;
	}

	public String getAlarm_flag() {
		return alarm_flag;
	}

	public void setAlarm_flag(String alarm_flag) {
		this.alarm_flag = alarm_flag;
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

	public String getAuth_email() {
		return auth_email;
	}

	public void setAuth_email(String auth_email) {
		this.auth_email = auth_email;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getStatement_msg() {
		return statement_msg;
	}

	public void setStatement_msg(String statement_msg) {
		this.statement_msg = statement_msg;
	}
	
}
