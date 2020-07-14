package kr.green.springtest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.springtest.dao.BoardDao;
import kr.green.springtest.vo.BoardVo;

@Service
public class BoardServiceImp implements BoardService {
	@Autowired
	private BoardDao boardDao;

	@Override
	public ArrayList<BoardVo> getBoardList() {
		return boardDao.getBoardList();
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
}
