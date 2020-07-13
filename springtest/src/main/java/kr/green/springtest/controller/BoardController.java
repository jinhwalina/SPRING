package kr.green.springtest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest.service.BoardService;
import kr.green.springtest.vo.BoardVo;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	
	public ModelAndView boardListGet(ModelAndView mv) {
		mv.setViewName("/board/list");
		ArrayList<BoardVo> list = boardService.getBoardList(); // board 게시판에 있는 글을 다 가져오는 
		// 왜 list 를 쓸까? => 순서대로 가져오기 위해
		// 왜 linked 가 아닌 Array일까? => 탐색이 빠름. DB에서 한 데이터를 가져와서 보여주기때문에 굳이..  삽입 삭제가 빠른건 linked
		mv.addObject("list",list);
		return mv;
	}

}
