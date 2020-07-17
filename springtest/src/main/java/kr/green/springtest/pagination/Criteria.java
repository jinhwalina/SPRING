package kr.green.springtest.pagination;

public class Criteria {
		//현재 페이지
		private int page; 
		//한 페이지 당 컨텐츠 갯수
		private int perPageNum;
		//Criteria 디폴트 생성자 : 현재 페이지를 1페이지로, 한 페이지에 10개의 컨텐츠
		private String search; // 검색.
		
		public String getSearch() {
			return search;
		}
		public void setSearch(String search) {
			this.search = search;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) { // 전체 4개의 타입이 존재
			if(type < 0 || type > 3)
				this.type = 0 ;
			else
				this.type = type;
			// 위 네 줄을 한 줄로 작성할 경우에는 조건선택 연산자로 this.type = (type < 0 || type > 3) ? 1 : type;
		}
		private int type; // int가 될수도 있고 , String이 될수도있음.
		
		public Criteria() {
			this.page = 1;
			this.perPageNum = 10;
			search = "";
			type = 0; // 생략가능. 왜냐하면 int는 기본 초기값이 0이기 때문에.
		}
		public int getPage() {
			return page;
		}
		public void setPage(int page) { // 왜 수정해야할까 ? 페이지가 1페이지부터 시작하기 때문에, 0 이하의 숫자가 오면 안되기때문에 처리를 해줘야한다.
			if(page <= 0) { // 0 대신 1 로 써도 된다. 어차피 1부터 시작하기때문에
				this.page = 0;
			} else 
				this.page = page;
		}
		public int getPerPageNum() {
			return perPageNum;
		}
		public void setPerPageNum(int perPageNum) { // 한 페이지에 컨텐츠 갯수! 보여줄 컨텐츠를 설정해준다
			if(perPageNum < 10) { // 최소 컨텐츠가 10개가 나오도록!
				this.perPageNum = 10;
			} else
				this.perPageNum = perPageNum;
		}

		@Override
		public String toString() {
			return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", search=" + search + ", type=" + type
					+ "]";
		}
		
		public int getStartPage() { // mapper의 limit에서 사용해줌.
			return (this.page -1) * perPageNum;
		}
		
	
}
