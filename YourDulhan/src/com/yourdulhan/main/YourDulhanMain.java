package com.yourdulhan.main;

import com.yourdulhan.daoImpl.AddressImpl;
import com.yourdulhan.daoImpl.HallImpl;
import com.yourdulhan.daoImpl.MemberImpl;
import com.yourdulhan.pojo.Address;
import com.yourdulhan.pojo.Hall;
import com.yourdulhan.pojo.Member;

public class YourDulhanMain {
	
	public static void main(String[] args) {
		
		/*Member obj=new Member();
		//obj.setMemberId(1);
		obj.setFirstName("Riya");
		obj.setLastName("Khan");
		obj.setEmailId("riyakhan@gmail.com");
		obj.setGender("Female");
		obj.setDob("11-01-1992");
		obj.setIsActive(1);
		obj.setMobileNumber("9788945028");
		obj.setPassword("Abc@123");*/
	
		
		//MemberImpl impl=new MemberImpl();
		//impl.addMember(obj);
		//impl.isActiveMember(0, 1);
		//impl.updateMember(obj, 3);
		//impl.getMemberList();
		//impl.getMemberDetailsById(4);
		
		
		Hall h=new Hall();
		HallImpl himpl=new HallImpl();
		h.setHallName("Anusaya Hall");
		h.setOwnerFirstName("Ramesh");
		h.setOwnerLastName("Zaa");
		h.setMobileNumber("98777878");
		
		
		Address a=new Address();
		a.setCity("Ramtek");
		a.setLandMark("Tin Taki");
		a.setPincode("441108");
		a.setState("Maha");
		a.setStreetName("Abhinav Bindra Marg");
		AddressImpl aimpl=new AddressImpl();
		aimpl.saveAddres(a);
		//himpl.addHall(h);
		
		
		
		
		
		
		
	}

}
