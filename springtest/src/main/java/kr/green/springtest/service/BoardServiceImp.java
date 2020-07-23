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

	@Override
	public int updateUp(int num, String id) {
		//추천 테이블에서 게시글 번호와 아이디와 일치하는 값이 있는 검색해서
		//이미 추천했다면 -1을 리턴
		if(boardDao.selectUp(num, id) != 0)
			return -1;
		//추천 테이블에 추천을 등록
		boardDao.insertUp(num, id);
		//게시글의 추천수만 업데이트
		boardDao.updateBoardByUp(num);
		//게시글 정보를 가져옴
		BoardVo board = boardDao.getBoard(num);
		return board.getUp();
	}
}
