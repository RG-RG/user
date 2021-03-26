package kr.co.rgrg.user.member.vo;

public class SocialJoinVO {

	private String id, auth_email, nickname, blog_name, profile_img, platform, access_token;

	public String getId() {
		return id;
	}

	public String getAuth_email() {
		return auth_email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getBlog_name() {
		return blog_name;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public String getPlatform() {
		return platform;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAuth_email(String auth_email) {
		this.auth_email = auth_email;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setBlog_name(String blog_name) {
		this.blog_name = blog_name;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
}//SocialJoinVO
