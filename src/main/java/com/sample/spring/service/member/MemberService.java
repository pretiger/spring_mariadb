package com.sample.spring.service.member;

import java.util.List;
import java.util.Map;

import com.sample.spring.domain.member.Member;

public interface MemberService {

	public void delete(String userid);
	public void insert(Member dto);
	public void update(Member dto);
	public String passChk(String userid, String passwd);
	public Member view(String userid);
	public List<Member> list();
}
