package kr.spring.controller.pagination;

public class Criteria {
	private int page;
	private int perPageNum;
	
	public Criteria() { // 굳이 매개변수 필요 없음
		this.page = 1;
		this.perPageNum = 3;
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
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	public int getPageStart() { // getPageStart() 는 언제 호출 될까!
		return (page - 1) * perPageNum;
	}
}
