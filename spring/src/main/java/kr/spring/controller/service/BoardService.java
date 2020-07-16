package kr.spring.controller.service;

import java.util.ArrayList;

import kr.spring.controller.pagination.Criteria;
import kr.spring.controller.pagination.PageMaker;
import kr.spring.vo.BoardVo;

public interface BoardService {

	ArrayList<BoardVo> getBoardList(Criteria cri);

	BoardVo getBoard(Integer num);

	void increaseViews(Integer num);

	void registerBoard(BoardVo board);

	void updateBoard(BoardVo board);

	void deleteBoard(Integer num);

	PageMaker getPageMaker(Criteria cri);

}
