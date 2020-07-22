package com.sample.spring.mapper;

import java.util.List;
import java.util.Map;

import com.sample.spring.domain.board.Board;
import com.sample.spring.domain.board.Reply;

public interface BoardMapper {

	public void plusStep(Map<String, Object> map);
	public void insertAnswer(Board dto);
	public void plusCount(int num);
	public List<Reply> listReply(int bnum);
	public void insertReply(Reply dto);
	public void deleteReply(int bnum);
	public void delete(int num);
	public void update(Board dto);
	public void deleteAttach(String fullname);
	public List<String> listAttach(int bnum);
	public Board view(int num);
	public void insertAttach(Map<String, Object> map);
	public void insert(Board dto);
	public String preview(int num);
	public int count(Map<String, String> map);
	public List<Board> list(Map<String, Object> map);
}
