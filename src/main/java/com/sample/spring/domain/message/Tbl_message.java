package com.sample.spring.domain.message;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Tbl_message {
	private int mid;
	private String targetid;
	private String sender;
	private String message;
	private Date opendate;
	private Date senddate;
}
