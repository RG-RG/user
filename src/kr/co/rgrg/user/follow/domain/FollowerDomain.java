package kr.co.rgrg.user.follow.domain;

public class FollowerDomain {
	
	private String id, nickname, statement_msg, profile_img;

	public String getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public String getStatement_msg() {
		return statement_msg;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setStatement_msg(String statement_msg) {
		this.statement_msg = statement_msg;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

}//FollowerDomain
