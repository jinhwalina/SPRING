package kr.green.springtest.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.springtest.dao.BoardDao;
import kr.green.springtest.pagination.Criteria;
import kr.green.springtest.pagination.PageMaker;
import kr.green.springtest.vo.BoardVo;
import kr.green.springtest.vo.UserVo;

@Service
public class BoardServiceImp implements BoardService {
	@Autowired
	private BoardDao boardDao;

	@Override
	public ArrayList<BoardVo> getBoardList(Criteria cri) {
		return boardDao.getBoardList(cri);
	}

	@Override
	public BoardVo getBoard(Integer num) {
		if(num == null)
			return null;
		return boardDao.getBoard(num);
	}

	@Override
	public void insertBoard(BoardVo board) {
		boardDao.insertBoard(board);
		
	}
	@Override
	public BoardVo view(Integer num) { // getBoard + 조회수 증가 시키는 역할
		BoardVo board = getBoard(num);
		if(board != null) {
			board.setViews(board.getViews()+1);
			boardDao.updateBoard(board); // boardDao한테 일 을 시킴 ! 
		}
		return board;
	}

	@Override
	public void updateBoard(BoardVo board) {
		board.setIsDel('N');
		boardDao.updateBoard(board);
	}

	@Override
	public void deleteBoard(Integer num, UserVo user) {
		BoardVo board = boardDao.getBoard(num);
		if(board == null)
			return;
		if(!board.getWriter().equals(user.getId()))
			return;
		board.setIsDel('Y');
		board.setDelDate(new Date());
		boardDao.updateBoard(board);
	}

	@Override
	public PageMaker getPageMakerByBoard(Criteria cri) {
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(boardDao.getTotalCountByBoard(cri));
		return pm;
	}
}
