package com.sample.spring.service.message;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.spring.domain.message.Tbl_message;
import com.sample.spring.mapper.MessageMapper;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageMapper messageMapper;

	@Override
	public void create(Tbl_message dto) {
		messageMapper.create(dto);
	}

	@Override
	public void addMessage(Tbl_message dto) {
		messageMapper.create(dto);
		Map<String, Object> map=new HashMap<>();
		map.put("userid", dto.getSender());
		map.put("point", 10);
		messageMapper.updatePoint(map);
	}
	


}
