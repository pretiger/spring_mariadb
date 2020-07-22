package com.sample.spring.controller.grid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GridController {
//로컬에서 주석 삽입
	private static final Logger logger = LoggerFactory.getLogger(GridController.class);

	@RequestMapping("/gridSample.do")
	public String gridSample() {
		return "grid/list";
	}
}
