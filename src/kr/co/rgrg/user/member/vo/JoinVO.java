package kr.co.rgrg.user.member.vo;

public class JoinVO {
	
	private String id, pass, auth_email, nickname, blog_name;

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

}//JoinVO
