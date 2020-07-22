package com.sample.spring.mapper;

import java.util.List;
import java.util.Map;

import com.sample.spring.domain.member.Member;

public interface MemberMapper {

	public void delete(String userid);
	public void insert(Member dto);
	public void update(Member dto);
	public String passChk(Map<String, String> map);
	public Member view(String userid);
	public List<Member> list();
}
