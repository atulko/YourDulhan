package com.yourdulhan.main;

import com.yourdulhan.daoImpl.MemberImpl;
import com.yourdulhan.pojo.Member;

public class YourDulhanMain {
	
	public static void main(String[] args) {
		
		Member obj=new Member();
		//obj.setMemberId(1);
		obj.setFirstName("Riya");
		obj.setLastName("Khan");
		obj.setEmailId("riyakhan@gmail.com");
		obj.setGender("Female");
		obj.setDob("11-01-1992");
		obj.setIsActive(1);
		obj.setMobileNumber("9788945028");
		obj.setPassword("Abc@123");
	
		
		MemberImpl impl=new MemberImpl();
		//impl.addMember(obj);
		//impl.isActiveMember(0, 1);
		//impl.updateMember(obj, 3);
		//impl.getMemberList();
		impl.getMemberDetailsById(4);
		
		
		
		
	}

}
