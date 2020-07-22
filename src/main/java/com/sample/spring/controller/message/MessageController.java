package com.sample.spring.controller.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.domain.message.Tbl_message;
import com.sample.spring.service.message.MessageService;

@RestController
@RequestMapping("/messages/*")
public class MessageController {

	private static final Logger logger=LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	MessageService messageService;
	
	@PostMapping("addMessage.do")
	public ResponseEntity<String> addMessage(@RequestBody Tbl_message dto) {
		ResponseEntity<String> entity=null;
		try {
			messageService.addMessage(dto);
			entity=new ResponseEntity<>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
