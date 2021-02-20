package kr.co.rgrg.user.member.vo;

public class JoinVO {
	
	private String id, pass, auth_mail, nickname;

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

}//JoinVO
