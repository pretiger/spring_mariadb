package com.sample.spring.domain.board;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Board {
	private int num;
	private String writer;
	private String name;
	private String subject;
	private String content;
	private int subgroup;
	private int substep;
	private int sublevel;
	private int viewcount;
	private int cnt;
	private MultipartFile[] files;
	private Date regdate;
}
