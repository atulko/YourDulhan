package com.yourdulhan.dao;

import java.util.List;

import com.yourdulhan.pojo.Hall;
import com.yourdulhan.pojo.Member;

public interface IHall {
	
	boolean addHall(Hall hall);
	
	 boolean isActiveHall(int isActive,int hallId);
	 boolean updateHall(Hall hall,int hallId);
	 List<Hall> getHallList();
	 Hall getHallDetailsById(int hallId);
	

}
