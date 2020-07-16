package kr.spring.controller.pagination;

public class Criteria {
	private int page;
	private int perPageNum;
	private String search;
	private int type;
	
	public Criteria() { // 굳이 매개변수 필요 없음
		page = 1;
		perPageNum = 3;
		search = ""; // 기본 검색어는 없음
		type = 0;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		if(type < 0 || type > 3) 
			this.type = 0;
		else
			this.type = type;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <=0)
			this.page = 1;
		else
			this.page = page;
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		if(perPageNum < 1)
			this.perPageNum = 10;
		this.perPageNum = perPageNum;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", search=" + search + ", type=" + type + "]";
	}

	public int getPageStart() { // getPageStart() 는 언제 호출 될까!
		return (page - 1) * perPageNum;
	}
}
