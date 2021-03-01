package kr.co.rgrg.user.member.vo;

public class JoinVO {
	
	private String id, pass, auth_mail, nickname, blog_name;

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public String getAuth_mail() {
		return auth_mail;
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

	public void setAuth_mail(String auth_mail) {
		this.auth_mail = auth_mail;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setBlog_name(String blog_name) {
		this.blog_name = blog_name;
	}

}//JoinVO
