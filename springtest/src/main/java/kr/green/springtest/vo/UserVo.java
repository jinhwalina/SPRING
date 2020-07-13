package kr.green.springtest.vo;

public class UserVo {
	private String id;
	private String pw;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override // toString은 사실 없어도 되지만, 콘솔에서 편하게 확인하기 위해 추가해줌
	public String toString() {
		return "UserVo [id=" + id + ", pw=" + pw + "]";
	}
	
}
