package com.sample.spring.service.message;

import com.sample.spring.domain.message.Tbl_message;

public interface MessageService {

	public void create(Tbl_message dto);
	public void addMessage(Tbl_message dto);
}
