package kr.co.rgrg.user.blog.domain;

public class CommDomain {
	private String id, profile_img, nickname, comm_content, input_date;
	private int comm_num;

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

	public String getComm_content() {
		return comm_content;
	}

	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}

	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}

	public int getComm_num() {
		return comm_num;
	}

	public void setComm_num(int comm_num) {
		this.comm_num = comm_num;
	}

	@Override
	public String toString() {
		return "CommDomain [id=" + id + ", profile_img=" + profile_img + ", nickname=" + nickname + ", comm_content="
				+ comm_content + ", input_date=" + input_date + ", comm_num=" + comm_num + ", getId()=" + getId()
				+ ", getProfile_img()=" + getProfile_img() + ", getNickname()=" + getNickname() + ", getComm_content()="
				+ getComm_content() + ", getInput_date()=" + getInput_date() + ", getComm_num()=" + getComm_num()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}//class
