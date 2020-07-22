package com.sample.spring.domain.board;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Reply {
	private int rnum;
	private int bnum;
	private String replyer;
	private String name;
	private String replytext;
	private Date regdate;
}
