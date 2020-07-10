package kr.spring.controller;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.controller.service.BoardService;
import kr.spring.vo.BoardVo;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ModelAndView boardListGet(ModelAndView mv) {
		logger.info("URI:/board/list");
		mv.setViewName("/board/list"); 
		
		ArrayList<BoardVo> list;
		list = boardService.getBoardList();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public ModelAndView boardDetailGet(ModelAndView mv, Integer num) {
		logger.info("URI:/board/detail");
		mv.setViewName("/board/detail");
		BoardVo board = null;
		if(num!=null) {
			board = boardService.getBoard(num);
			mv.addObject("board",board);
			if(board != null) {
				boardService.increaseViews(num);
				board.setViews(board.getViews()+1);
			}
		}
		return mv;
	}
	
	@RequestMapping(value = "/board/register", method = RequestMethod.GET)
	public ModelAndView boardRegisterGet(ModelAndView mv) {
		logger.info("URI:/board/register:GET");
		mv.setViewName("/board/register"); 
		return mv;
	}
	
	@RequestMapping(value = "/board/register", method = RequestMethod.POST) /* 메서드 이름에 따라 전송받을때 어디로 받을지 결정된다. */
	public ModelAndView boardRegisterPost(ModelAndView mv, BoardVo board) { /* name의 이름과 멤버변수가 일치하는게 있으면 자동으로 넘겨준다. */
		logger.info("URI:/board/register:POST");
		mv.setViewName("redirect:/board/list"); 
		boardService.registerBoard(board);
		return mv;
	}
	
	@RequestMapping(value = "/board/modify", method = RequestMethod.GET)
	public ModelAndView boardModifyGet(ModelAndView mv, Integer num) {
		logger.info("URI:/board/modify:GET");
		mv.setViewName("/board/modify");
		System.out.println(num);
		BoardVo board = null;
		if(num != null) {
			board = boardService.getBoard(num);
		}
		mv.addObject("board",board);
		return mv;
	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public ModelAndView boardModifyPost(ModelAndView mv, BoardVo board) {
		logger.info("URI:/board/modify:POST");
		mv.setViewName("redirect:/board/list"); /* 수정이 끝나면 어디로 보낼지 정해주는 코드 이건 list로 보냄*/
		boardService.updateBoard(board);
		return mv;
	}
	
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public ModelAndView boardDeleteGet(ModelAndView mv, Integer num) {
		logger.info("URI:/board/delete:GET");
		mv.setViewName("redirect:/board/list");
		boardService.deleteBoard(num);
		return mv;
	}
	
}
