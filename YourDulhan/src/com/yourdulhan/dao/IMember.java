package com.yourdulhan.dao;

import java.util.List;

import com.yourdulhan.pojo.Member;

public interface IMember {
	
 boolean addMember(Member member);
 boolean isActiveMember(int isActive,int memberId);
 boolean updateMember(Member member,int memberId);
 List<Member> getMemberList();
 Member getMemberDetailsById(int memberId);

}
