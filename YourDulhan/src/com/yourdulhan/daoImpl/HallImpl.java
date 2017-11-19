package com.yourdulhan.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.yourdulhan.dao.IHall;
import com.yourdulhan.pojo.Hall;
import com.yourdulhan.util.DBConnection;

public class HallImpl implements IHall{
	public static final String INSERT_HALL="insert into marriage_hall(hall_name,owner_first_name,owner_last_name,mobile_number,address_id)values(?,?,?,?,?)";
	public static final String IS_HALL_EXIST="select * from marriage_hall where hall_name=? and address_id=?";

	@Override
	public boolean addHall(Hall hall)
	{
		boolean flag=false;
		Connection conn=null;
		PreparedStatement preSts=null;
		ResultSet rs=null;
		int hallId=0;
		try {
			boolean result=HallImpl.isHallExist(hall);
			if(result)
			{
				flag=false;
			}else
			{			
			conn=DBConnection.getDbConnection();
			conn.setAutoCommit(false);
			preSts=conn.prepareStatement(INSERT_HALL, PreparedStatement.RETURN_GENERATED_KEYS);
			int pos=0;
			preSts.setString(++pos, hall.getHallName());
			preSts.setString(++pos, hall.getOwnerFirstName());
			preSts.setString(++pos, hall.getOwnerLastName());
			preSts.setString(++pos, hall.getMobileNumber());
			int addressId=AddressImpl.saveAddres(hall.getAddress());
			preSts.setInt(++pos, addressId);
			preSts.executeUpdate();
			rs=preSts.getGeneratedKeys();
			if(rs.next())
			{
				conn.commit();
				hallId=rs.getInt(1);
			}if(hallId>0)
			{
				flag=true;
				System.out.println("Hall is addedd successfully");
			}else
			{
				System.out.println("Could Not add hall");
			}			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null)
				try {
					conn.close();			
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}if(preSts!=null)
				{
					try {
						
						preSts.close();
						
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
		}
		
		
		return flag;
	}

	public static boolean isHallExist(Hall hall)
	{
		boolean flag=false;
		Connection conn=null;
		PreparedStatement preSts=null;
		ResultSet rs=null;
		try {
			conn=DBConnection.getDbConnection();
			preSts=conn.prepareStatement(IS_HALL_EXIST);
			int pos=0;
			preSts.setString(++pos, hall.getHallName());
			preSts.setInt(++pos, hall.getAddressId());
			rs=preSts.executeQuery();
			while(rs.next())
			{
				flag=true;
				System.out.println("This hall is already exist");
			}		
			
		} catch (Exception e) {
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();				
					}
			}
			if(preSts!=null)
			{
				try {
					preSts.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	
	@Override
	public boolean isActiveHall(int isActive, int hallId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateHall(Hall hall, int hallId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Hall> getHallList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hall getHallDetailsById(int hallId) {
		// TODO Auto-generated method stub
		return null;
	}

}
