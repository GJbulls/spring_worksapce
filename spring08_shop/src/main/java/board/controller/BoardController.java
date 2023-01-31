package board.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.dto.BoardDTO;
import board.dto.PageDTO;
import board.service.BoardService;
import common.file.FileUpload;



@Controller
public class BoardController {
   
   private BoardService boardService;
   private PageDTO pdto;
   
   public BoardController() {
      
   }
   
   public void setBoardService(BoardService boardService) {
      this.boardService = boardService;
   }
   
   @RequestMapping("/board/list.do")
   public ModelAndView listExecute(@ModelAttribute("pv") PageDTO pv, ModelAndView mav) {
	   System.out.println("pv:" + pv.getCurrentPage());
	   int totalRecord = boardService.countProcess();
      if(totalRecord >= 1) {
    	  if(pv.getCurrentPage()==0)
    		  pv.setCurrentPage(1);
    	  this.pdto = new PageDTO(pv.getCurrentPage(), totalRecord);
    	  mav.addObject("pv",this.pdto);
    	  mav.addObject("aList", boardService.listProcess(this.pdto));
      }
    	  
      mav.setViewName("board/list"); //받아온 값을 list.jsp로 전달
      return mav;
   }
   
   @RequestMapping(value = "/board/write.do", method=RequestMethod.GET)
   public ModelAndView writeExecute(@ModelAttribute("dto") BoardDTO dto, @ModelAttribute("pv") PageDTO pv, ModelAndView mav) {
	   mav.setViewName("board/write");
      return mav;
   }

   @RequestMapping(value="/board/write.do", method=RequestMethod.POST)
   public String writeProExecute(BoardDTO dto, PageDTO pv, HttpServletRequest req, RedirectAttributes ratt) {
      //첨부파일 작업
      MultipartFile file = dto.getFilename();
//      System.out.println("file : " + file.getOriginalFilename());
      
      //(FileUpload.java) 
      //동일한 첨부파일을 저장할 경우 중복이 되면 안되기 때문에 파일의 앞에 난수를 발생하여 저장하도록 한다.
      //첨부파일이 있으면... 
      if(!file.isEmpty()) {
         UUID random = FileUpload.saveCopyFile(file, req); //첨부파일에대한 정보를 받아옴
         dto.setUpload(random + "_" + file.getOriginalFilename());
      }
      
      dto.setIp(req.getRemoteAddr()); //클라이언트에 ip주소를 저장해줌
      
      boardService.insertProcess(dto);
      
      if(dto.getRef()!=0) {
    	  ratt.addAttribute("currentPage",pv.getCurrentPage());
      }
      
      return "redirect:/board/list.do";
   }
   
   @RequestMapping("/board/view.do")
   public ModelAndView viewExecute(int currentPage, int num, ModelAndView mav) {
	   System.out.printf("currentPage:%d, num:%d\n", currentPage,num);
	   
	   mav.addObject("dto",boardService.contentProcess(num));
	   mav.addObject("currentPage",currentPage);
	   mav.setViewName("board/view");
	   return mav;
   }
   
   @RequestMapping(value="/board/update.do", method=RequestMethod.GET)
   public ModelAndView updateExecute(int num, int currentPage, ModelAndView mav) {
	   
	   mav.addObject("dto",boardService.updateSelectProcess(num));
	   mav.addObject("currentPage",currentPage);
	   mav.setViewName("board/update");
	   return mav;
   }
   
   
   
   
   @RequestMapping(value="/board/update.do", method=RequestMethod.POST)
   public String updateExecute(@ModelAttribute("dto") BoardDTO dto, int currentPage, HttpServletRequest request, RedirectAttributes ratt) {
	   MultipartFile file = dto.getFilename();
	   if(!file.isEmpty()){
		   UUID random = FileUpload.saveCopyFile(file, request);
		   dto.setUpload(random + "_" + file.getOriginalFilename());
	   }
	   
	   boardService.updateProcess(dto, FileUpload.urlPath(request));
	   
	   //primitive 값만 가능
	   //return "redirect:/board/list.do?currentPage="+currentPage;
	   //ratt.addAttribute("currentPage",currentPage);
	   
	   //object 값 가능(section에 담아서 넘겨준다.)
//	   ratt.addFlashAttribute("dto",dto);
	   
	   ratt.addAttribute("currentPage",currentPage);
	   return "redirect:/board/list.do";
   }
   
   @RequestMapping("/board/delete.do")
   public String deleteExecute(int num, int currentPage, HttpServletRequest request, RedirectAttributes ratt) {
	   ratt.addAttribute("currentPage",currentPage);
	   boardService.deleteProcess(num,FileUpload.urlPath(request));
	   return "redirect:/board/list.do";
   }
   
   @RequestMapping("/board/contentdownload.do")
   public ModelAndView downloadExecute(int num, ModelAndView mav) {
	   mav.addObject("num", num);
	   mav.setViewName("download");
	   return mav;
	   
   }
}//end class
   
   
   
   
   