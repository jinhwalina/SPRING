package kr.green.springtest.dao;

import java.util.ArrayList;

import kr.green.springtest.vo.BoardVo;

public interface BoardDao {

	ArrayList<BoardVo> getBoardList();

}
