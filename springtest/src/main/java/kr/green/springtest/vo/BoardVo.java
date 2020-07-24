package kr.green.springtest.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVo {
	private int num;
	private String title;
	private String content;
	private String writer;
	private Date registerDate;
	private Date delDate;
	private char isDel;
	private int views;
	private int up;
	private String file;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegisterDate() { // 날짜 형식을 문자열 형태로 
		SimpleDateFormat transFormat = 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = transFormat.format(registerDate);
		return date;
		// return transFormat.format(registerDate);  이렇게 써도 상관 없음
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public void setRegisterDate(String registerDate) {
		SimpleDateFormat transFormat
			= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.registerDate = transFormat.parse(registerDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	public char getIsDel() {
		return isDel;
	}
	public void setIsDel(char isDel) {
		this.isDel = isDel;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	@Override
	public String toString() {
		return "BoardVo [num=" + num + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", registerDate=" + registerDate + ", delDate=" + delDate + ", isDel=" + isDel + ", views=" + views
				+ ", up=" + up + ", file=" + file + "]";
	}
	public String getOriFile() {
		// 파일 이름보면 /월/일/ 이런식으로 표현되는데 , 뒤에 파일명만 보여야하는데 이 본래 파일명만 보여주게함
		// DB에 저장된 file이름은 /년도/월/일/uuid_파일명.확장자 로 되어있는데, 사용자는 파일명.확장자 만 보여줘야하기 때문에 getOriFile을 통해 원본 파일명을 알려준다.
		int index = file.indexOf("_");
		return file.substring(index+1); 
	}
	
}
