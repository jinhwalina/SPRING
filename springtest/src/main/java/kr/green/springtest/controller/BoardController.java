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
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public ModelAndView boardDetailGet(ModelAndView mv, Integer num) {
		mv.setViewName("/board/detail");
		//BoardVo board = boardService.getBoard(num); // 번호를서비스한테 넘겨주면, 서비스는 그에 맞는 정보를 넘겨준다.
		// getBoard는 글 수정하고 쓸 때 사용할 수 있기 때문에, 지우지는 않고 남겨준다.
		BoardVo board = boardService.view(num); // 조회수 증가를 추가하기위함
		mv.addObject("board",board); // board의 board를 클라이언트한테 보내준다.
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.GET)
	public ModelAndView boardRegisterGet(ModelAndView mv) { // 넘어오는 정보 없음 
		mv.setViewName("/board/register"); 
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.POST) 
	public ModelAndView boardRegisterPost(ModelAndView mv, BoardVo board) {
		mv.setViewName("redirect:/board/list"); // 등록을 하면 보내는곳 > list
		boardService.insertBoard(board); // 일을 시킴
		return mv;
	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.GET)
	public ModelAndView boardModifyGet(ModelAndView mv, Integer num) { // 넘어오는 정보 없음 
		mv.setViewName("/board/modify");
		BoardVo board = boardService.getBoard(num);
		mv.addObject("board",board);
		return mv;
	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public ModelAndView boardModifyGet(ModelAndView mv, BoardVo board) {
		mv.setViewName("redirect:/board/list");
		boardService.updateBoard(board);
		return mv;
	}
	

}