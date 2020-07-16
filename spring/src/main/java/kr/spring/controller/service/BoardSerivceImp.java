package kr.spring.controller.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.controller.dao.BoardDao;
import kr.spring.controller.pagination.Criteria;
import kr.spring.controller.pagination.PageMaker;
import kr.spring.vo.BoardVo;

@Service
public class BoardSerivceImp implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public ArrayList<BoardVo> getBoardList(Criteria cri) {
		return boardDao.getBoardList(cri);
	}

	@Override
	public BoardVo getBoard(Integer num) {
		return boardDao.getBoard(num);
	}

	@Override
	public void increaseViews(Integer num) {
		boardDao.increaseViews(num);
	}

	@Override
	public void registerBoard(BoardVo board) {
		boardDao.registerBoard(board);
		
	}

	@Override
	public void updateBoard(BoardVo board) {
		board.setIsDel('N');
		boardDao.updateBoard(board);
		
	}
	
	
	@Override
	public void deleteBoard(Integer num) {
		if(num != null) {
			BoardVo board = boardDao.getBoard(num);
			if(board != null) {
				board.setIsDel('Y');
				board.setDelDate(new Date());
				boardDao.updateBoard(board);
			}
		}
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) {
		PageMaker pm = new PageMaker();
		int totalCount = boardDao.getTotalCount(); // 전체 갯수를 가져오도록 오청함
		pm.setCriteria(cri);
		pm.setTotalCount(totalCount);
		return pm;
	}
	 
	
		
	
}
