package com.sample.spring.service.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.spring.domain.member.Member;
import com.sample.spring.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public List<Member> list() {
		return memberMapper.list();
	}

	@Override
	public Member view(String userid) {
		return memberMapper.view(userid);
	}

	@Override
	public String passChk(String userid, String passwd) {
		Map<String, String> map=new HashMap<>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		return memberMapper.passChk(map);
	}

	@Override
	public void update(Member dto) {
		memberMapper.update(dto);
	}

	@Override
	public void insert(Member dto) {
		memberMapper.insert(dto);
	}

	@Override
	public void delete(String userid) {
		memberMapper.delete(userid);
	}

}
