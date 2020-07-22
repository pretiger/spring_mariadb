package com.sample.spring.service.board;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sample.spring.domain.board.Board;
import com.sample.spring.domain.board.Reply;
import com.sample.spring.mapper.BoardMapper;
import com.sample.spring.utils.UploadFileUtils;

@Service
public class BoardServiceImpl implements BoardService {

	@Resource(name="uploadPath")
	String uploadPath;
	
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<Board> list(int start, int end, String searchkey, String keyword) {
		Map<String, Object> map=new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("searchkey", searchkey);
		map.put("keyword", "%"+keyword+"%");
		return boardMapper.list(map);
	}

	@Override
	public int count(String searchkey, String keyword) {
		Map<String, String> map=new HashMap<>();
		map.put("searchkey", searchkey);
		map.put("keyword", "%"+keyword+"%");
		return boardMapper.count(map);
	}

	@Override
	public String preview(int num) {
		return boardMapper.preview(num);
	}

	@Override
	@Transactional
	public void insert(Board dto) {
		boardMapper.insert(dto);
		if(dto.getFiles() != null) {
			MultipartFile[] files=dto.getFiles();
			for(MultipartFile file : files) {
				if(file.getSize() > 0) {
					String originalFileName=file.getOriginalFilename();
					String fullname="";
					int bnum=dto.getNum();
					try {
						fullname=UploadFileUtils.uploadFile(uploadPath, originalFileName, file.getBytes());
						Map<String, Object> map=new HashMap<>();
						map.put("fullname", fullname);
						map.put("bnum", bnum);
						boardMapper.insertAttach(map);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public Board view(int num) {
		return boardMapper.view(num);
	}

	@Override
	public List<String> listAttach(int bnum) {
		return boardMapper.listAttach(bnum);
	}

	@Override
	public void deleteAttach(String fullname) {
		boardMapper.deleteAttach(fullname);
	}

	@Override
	@Transactional
	public void update(Board dto) {
		boardMapper.update(dto);
		if(dto.getFiles() != null) {
			MultipartFile[] files=dto.getFiles();
			for(MultipartFile file : files) {
				if(file.getSize() > 0) {
					String originalFileName=file.getOriginalFilename();
					String fullname="";
					int bnum=dto.getNum();
					try {
						fullname=UploadFileUtils.uploadFile(uploadPath, originalFileName, file.getBytes());
						Map<String, Object> map=new HashMap<>();
						map.put("fullname", fullname);
						map.put("bnum", bnum);
						boardMapper.insertAttach(map);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	@Transactional
	public void delete(Board dto) {
		if(dto.getCnt() > 0) {
			boardMapper.deleteReply(dto.getNum());
		}
		List<String> fullnames=boardMapper.listAttach(dto.getNum());
		if(fullnames != null) {
			for(String fullname : fullnames) {
				boardMapper.deleteAttach(fullname);
				new File(uploadPath+fullname.replace('/', File.separatorChar)).delete();
			}
		}
		boardMapper.delete(dto.getNum());
	}

	@Override
	public void insertReply(Reply dto) {
		boardMapper.insertReply(dto);
	}

	@Override
	public List<Reply> listReply(int bnum) {
		return boardMapper.listReply(bnum);
	}

	@Override
	public void plusCount(int num, HttpSession session) {
		long read_time=0;
		if(session.getAttribute("read_time_"+num) != null) {
			read_time=(long)session.getAttribute("read_time_"+num);
		}
		long current_time=System.currentTimeMillis();
		if(current_time - read_time > 5*1000) {
			boardMapper.plusCount(num);
			session.setAttribute("read_time_"+num, current_time);
		}
	}

	@Override
	@Transactional
	public void insertAnswer(Board dto) {
		dto.setSubstep(dto.getSubstep()+1);
		dto.setSublevel(dto.getSublevel()+1);
		Map<String, Object> map=new HashMap<>();
		map.put("subgroup", dto.getSubgroup());
		map.put("substep", dto.getSubstep());
		boardMapper.plusStep(map);
		boardMapper.insertAnswer(dto);
	}
}
