package com.sample.spring.controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.spring.domain.board.Board;
import com.sample.spring.domain.board.Reply;
import com.sample.spring.service.board.BoardService;
import com.sample.spring.service.board.ExcelService;
import com.sample.spring.utils.MediaUtils;
import com.sample.spring.utils.Pager;
// local comments insert
//2 update
@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger=LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	ExcelService excelService;
	
	@GetMapping("excelDownload")
	public String excelDownload(Model model, @RequestParam(defaultValue="1") int currentPage,
															 @RequestParam(defaultValue="all") String searchkey,
															 @RequestParam(defaultValue="") String keyword) {
		int count=boardService.count(searchkey, keyword);
		Pager pager=new Pager(count, currentPage);
		int start=pager.getPageStart()-1;
		int end=pager.getPageScale();
		List<Board> list = boardService.list(start, end, searchkey, keyword);
        
        SXSSFWorkbook workbook = excelService.excelFileDownloadBoard(list);
        
        model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "Board_List");
        return "excelDownloadView";
	}
	
	@PostMapping("insertAnswer.do")
	public String insertAnswer(@ModelAttribute Board dto, HttpSession session) {
		dto.setWriter((String)session.getAttribute("userid"));
		boardService.insertAnswer(dto);
		return "redirect:/board/list.do";
	}
	
	@GetMapping("answer.do/{num}")
	public String answer(@PathVariable int num, Model model) {
		Board dto=boardService.view(num);
		dto.setSubject("Re : "+dto.getSubject());
		dto.setContent("==========본문내용==========\n"+dto.getContent());
		model.addAttribute("dto", dto);
		return "board/answer";
	}
	
	@GetMapping("replyList.do/{bnum}")
	public String replyList(@PathVariable int bnum, Model model) {
		model.addAttribute("list", boardService.listReply(bnum));
		return "board/listReply";
	}
	
	@ResponseBody
	@PostMapping("insertReply.do")
	public void insertReply(@ModelAttribute Reply dto) {
		boardService.insertReply(dto);
	}
	
	@PostMapping
	public String delete(@ModelAttribute Board dto) {
		boardService.delete(dto);
		return "redirect:/board/list.do";
	}
	
	@PostMapping("update.do")
	public String update(@ModelAttribute Board dto) {
		boardService.update(dto);
		return "redirect:/board/list.do";
	}
	
	@ResponseBody
	@GetMapping("deleteFile")
	public ResponseEntity<String> deleteFile(String filename) {
		String fileExtention=filename.substring(filename.lastIndexOf(".")+1);
		MediaType mediaType=MediaUtils.getMediaType(fileExtention);
		if(mediaType != null) {
			String start=filename.substring(0, 12);
			String end=filename.substring(14);
			new File(uploadPath+(start+end).replace('/', File.separatorChar)).delete();
		}
		new File(uploadPath+filename.replace('/', File.separatorChar)).delete();
		boardService.deleteAttach(filename);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("downloadFile")
	public ResponseEntity<byte[]> downloadFile(String filename) throws Exception {
		InputStream inputStream=null;
		HttpHeaders httpHeaders=new HttpHeaders();
		try {
			inputStream=new FileInputStream(uploadPath+filename);
			filename=filename.substring(filename.indexOf("_")+1);
			filename=new String(filename.getBytes("utf-8"), "8859_1");
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			httpHeaders.add("Content-Disposition", "attachment;filename=\""+filename+"\"");
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if(inputStream != null) inputStream.close();
		}
		
	}
	
	@GetMapping("attachList.do/{bnum}")
	public @ResponseBody List<String> attachList(@PathVariable int bnum) {
		return boardService.listAttach(bnum);
	}
	
	@GetMapping("view.do/{num}")
	public String view(@PathVariable int num, Model model, HttpSession session) {
		boardService.plusCount(num, session);
		model.addAttribute("dto", boardService.view(num));
		return "board/view";
	}
	
	@PostMapping("insert.do")
	public String insert(@ModelAttribute Board dto, HttpSession session) {
		dto.setWriter((String)session.getAttribute("userid"));
		boardService.insert(dto);
		return "redirect:/board/list.do";
	}
	
	@GetMapping("write.do")
	public String write() {
		return "board/write";
	}
	
	@GetMapping("preview.do")
	public void preview(@RequestParam int num, HttpServletResponse response) throws Exception {
		String content=boardService.preview(num);
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter printWriter=response.getWriter();
		printWriter.print(content);
	}
	
	@GetMapping("list.do")
	public String list(Model model, @RequestParam(defaultValue="1") int currentPage,
							 @RequestParam(defaultValue="all") String searchkey,
							 @RequestParam(defaultValue="") String keyword) {
		int count=boardService.count(searchkey, keyword);
		Pager pager=new Pager(count, currentPage);
		int start=pager.getPageStart()-1;
		int end=pager.getPageScale();
		logger.info("=======start:"+start+"========end:"+end);
		model.addAttribute("list", boardService.list(start, end, searchkey, keyword));
		model.addAttribute("searchkey", searchkey);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", pager);
		return "board/list";
	}
}
