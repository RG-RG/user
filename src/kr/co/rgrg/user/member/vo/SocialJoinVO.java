package kr.co.rgrg.user.member.vo;

public class SocialJoinVO {

	private String id, auth_email, platform, access_token;

	public String getId() {
		return id;
	}

	public String getAuth_email() {
		return auth_email;
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

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	
}//SocialJoinVO
