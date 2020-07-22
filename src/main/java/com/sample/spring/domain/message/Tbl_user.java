package com.sample.spring.domain.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Tbl_user {
	private String userid;
	private String upw;
	private String uname;
	private int upoint;
}
