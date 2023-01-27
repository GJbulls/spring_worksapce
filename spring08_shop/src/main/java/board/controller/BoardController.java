package board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import board.dto.PageDTO;
import board.service.BoardService;

@Controller
public class BoardController {
	
	private BoardService boardService;
	
	public BoardController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("/board/list.do")
	public ModelAndView listExecute(PageDTO pv, ModelAndView mav) {
		int totalRecord = boardService.countProcess();
		
		mav.addObject("count",totalRecord);
		mav.setViewName("board/list");
		
		return mav;
	}
	
}//end class
