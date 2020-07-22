package com.sample.spring.mapper;

import java.util.Map;

import com.sample.spring.domain.message.Tbl_message;

public interface MessageMapper {

	public void create(Tbl_message dto);
	public void updatePoint(Map map);
}
