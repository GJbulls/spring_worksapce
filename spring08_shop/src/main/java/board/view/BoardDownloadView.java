package board.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import board.dao.BoardDAO;
import common.file.FileUpload;

//다운로드 창을 띄우기 위한 뷰 페이지
public class BoardDownloadView extends AbstractView {
	
	private BoardDAO boardDao;
	
	public BoardDownloadView() {
	
	}
	
	public void setBoardDao(BoardDAO boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String saveDirectory = FileUpload.urlPath(request);
		
		//첨부파일명 가져옴
		String upload = boardDao.getFile(num);
		//_다음부터 데이터값을 가져옴 실제 파일명을 가져옴
		String fileName = upload.substring(upload.indexOf("_")+1); 
		//첨부파일명이 한글일때 인코딩 작업을해야함 . 그렇지 않으면 한글이 꺠짐
		String str = URLEncoder.encode(fileName,"UTF-8");
		//공백이 + 로 나오기떄문에 공백으로 인식할 수 있도록 %20(공백)을 넣어줌.
		str = str.replace("\\+", "%20");
		//컨텐츠 타입,첨부파일이 정확하지 않고 다양할때(사진이나 doc,등등) 타입 설정
		response.setContentType("application/octet-stream");
		//다운로드 창에 보여줄 파일명을 지정한다. 
		response.setHeader("Content-Disposition","attachment;filename="+str +";"); 
		//서버에 저장된 파일을 읽어와 클라이언트에 출력해 준다.
		FileCopyUtils.copy(new FileInputStream(new File(saveDirectory, upload)), response.getOutputStream());
	
	
	
	}
}









