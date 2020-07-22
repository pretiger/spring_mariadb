package com.sample.spring.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sample.spring.domain.board.Board;
import com.sample.spring.domain.board.Reply;

public interface BoardService {

	public void insertAnswer(Board dto);
	public void plusCount(int num, HttpSession session);
	public List<Reply> listReply(int bnum);
	public void insertReply(Reply dto);
	public void delete(Board dto);
	public void update(Board dto);
	public void deleteAttach(String fullname);
	public List<String> listAttach(int bnum);
	public Board view(int num);
	public void insert(Board dto);
	public String preview(int num);
	public int count(String searchkey, String keyword);
	public List<Board> list(int start, int end, String searchkey, String keyword);
}
