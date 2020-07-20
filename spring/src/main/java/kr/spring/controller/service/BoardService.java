package kr.spring.controller.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.spring.controller.pagination.Criteria;
import kr.spring.controller.pagination.PageMaker;
import kr.spring.vo.BoardVo;
import kr.spring.vo.UserVo;

public interface BoardService {

	ArrayList<BoardVo> getBoardList(Criteria cri);

	BoardVo getBoard(Integer num);

	void increaseViews(Integer num);

	void registerBoard(BoardVo board, HttpServletRequest request);

	void updateBoard(BoardVo board, UserVo user);

	void deleteBoard(Integer num, UserVo userVo);

	PageMaker getPageMaker(Criteria cri);

}
