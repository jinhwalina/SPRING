package kr.green.springtest.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.green.springtest.controller.utils.UploadFileUtils;
import kr.green.springtest.pagination.Criteria;
import kr.green.springtest.pagination.PageMaker;
import kr.green.springtest.service.BoardService;
import kr.green.springtest.service.UserService;
import kr.green.springtest.vo.BoardVo;
import kr.green.springtest.vo.UserVo;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	UserService userService;
	
	private String uploadPath = "D:\\이진화\\UploadFiles";
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ModelAndView boardListGet(ModelAndView mv, Criteria cri) {
		mv.setViewName("/board/list");
		PageMaker pm = boardService.getPageMakerByBoard(cri);
		ArrayList<BoardVo> list = boardService.getBoardList(cri); // board 게시판에 있는 글을 다 가져오는 
		// 왜 list 를 쓸까? => 순서대로 가져오기 위해
		// 왜 linked 가 아닌 Array일까? => 탐색이 빠름. DB에서 한 데이터를 가져와서 보여주기때문에 굳이..  삽입 삭제가 빠른건 linked
		mv.addObject("list",list);
		mv.addObject("pm",pm);
		return mv;
	}
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public ModelAndView boardDetailGet(ModelAndView mv, Integer num, Criteria cri) {
		mv.setViewName("/board/detail");
		//BoardVo board = boardService.getBoard(num); // 번호를서비스한테 넘겨주면, 서비스는 그에 맞는 정보를 넘겨준다.
		// getBoard는 글 수정하고 쓸 때 사용할 수 있기 때문에, 지우지는 않고 남겨준다.
		BoardVo board = boardService.view(num); // 조회수 증가를 추가하기위함
		mv.addObject("board",board); // board의 board를 클라이언트한테 보내준다.
		mv.addObject("cri",cri);
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.GET)
	public ModelAndView boardRegisterGet(ModelAndView mv) { // 넘어오는 정보 없음 
		mv.setViewName("/board/register"); 
		return mv;
	}
	@RequestMapping(value = "/board/register", method = RequestMethod.POST) 
	public ModelAndView boardRegisterPost(ModelAndView mv, BoardVo board, HttpServletRequest r, MultipartFile file2 ) throws IOException, Exception {// jsp의 name과 이름 같게 해주기 
		mv.setViewName("redirect:/board/list"); // 등록을 하면 보내는곳 > list
		board.setWriter(userService.getUser(r).getId());
		String file = UploadFileUtils.uploadFile(uploadPath, file2.getOriginalFilename(),file2.getBytes());
		board.setFile(file);
		boardService.insertBoard(board); // 일을 시킴
		return mv;
	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.GET)
	public ModelAndView boardModifyGet(ModelAndView mv, Integer num, HttpServletRequest r) { // 넘어오는 정보 없음 
		mv.setViewName("/board/modify");
		BoardVo board = boardService.getBoard(num);
		UserVo user = userService.getUser(r);
		if(board == null || !user.getId().equals(board.getWriter()))
			mv.setViewName("redirect:/board/list");
		mv.addObject("board",board);
		return mv;
	}
	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public ModelAndView boardModifyGet(ModelAndView mv, BoardVo board, HttpServletRequest r, MultipartFile file2) throws IOException, Exception {
		mv.setViewName("redirect:/board/list");
		board.setWriter(userService.getUser(r).getId());
		// 새로운 첨부파일이 추가가 되면
		if(!file2.getOriginalFilename().contentEquals("")) {
			String fileName = UploadFileUtils.uploadFile(uploadPath, file2.getOriginalFilename(), file2.getBytes());
			board.setFile(fileName);
		}else if(board.getFile() == null ||board.getFile().equals("")) {
			board.setFile(null); 
		}
		boardService.updateBoard(board);
		return mv;
	}
	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	public ModelAndView boardDeleteGet(ModelAndView mv, Integer num, HttpServletRequest r) { 
		mv.setViewName("redirect:/board/list");
		boardService.deleteBoard(num, userService.getUser(r));
		return mv;
	}
	
	@RequestMapping(value ="/board/up", method=RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> boardUp(@RequestBody int num, HttpServletRequest r){
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    // 현재 로그인 중인 유저 정보
	    UserVo user = userService.getUser(r);
	    if(user == null) {
	    	map.put("isUser",false);
	    }else {
	    	map.put("isUser",true);
	    	int up = boardService.updateUp(num, user.getId());
	    	map.put("up",up);
	    }

	    return map;
	}
	@ResponseBody
	@RequestMapping(value="board/download") // 다운로드는 복붙 후 경로 지정해주면 된다.
	public ResponseEntity<byte[]> downloadFile(String fileName)throws Exception{
	    InputStream in = null;
	    ResponseEntity<byte[]> entity = null;
	    try{
	    	// HttpHeaders 객체 생성
	        HttpHeaders headers = new HttpHeaders();
	        // 다운로드 할 파일을 읽어옴
	        in = new FileInputStream(uploadPath+fileName);
	        // 다운로드시 저장 할 때 파일명 ! =을 통해서 덮어씀
	        fileName = fileName.substring(fileName.indexOf("_")+1);
	        // 헤더에 컨텐츠 타입을 설정
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        // 헤더 정보를 추가
	        headers.add("Content-Disposition",  "attachment; filename=\"" 
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
	        // ResponseEntity 객체 생성, 전송할 파일, 헤더정보, 헤더 상태
	        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
	    }catch(Exception e) {
	        e.printStackTrace();
	        entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST); // 파일을 못읽어 왔을 때 
	    }finally {
	        in.close();
	    }
	    return entity;
	}
	

}
