package com.yourdulhan.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yourdulhan.dao.IAddress;
import com.yourdulhan.pojo.Address;
import com.yourdulhan.util.DBConnection;



public class AddressImpl implements IAddress{
	public static final String INSERT_ADDRESS="insert into address(land_mark,street_name,city,state,pincode)values(?,?,?,?,?)";
	public static final String UPDATE_ADDRESS="update address set land_mark=? where address_id=?";

	
	public static int saveAddres(Address address)
	{ 
		boolean flag=false;
		Connection conn=null;
		PreparedStatement preSts=null;
		ResultSet rs=null;
		int addressId=0;
		try {
			conn=DBConnection.getDbConnection();
		    conn.setAutoCommit(false);
			preSts=conn.prepareStatement(INSERT_ADDRESS, PreparedStatement.RETURN_GENERATED_KEYS);
			int pos=0;
			preSts.setString(++pos,address.getLandMark());
			preSts.setString(++pos,address.getStreetName());
			preSts.setString(++pos,address.getCity());
			preSts.setString(++pos,address.getState());
			preSts.setString(++pos,address.getPincode());
			preSts.executeUpdate();
			rs=preSts.getGeneratedKeys();
			while(rs.next())
			{
				conn.commit();
			    addressId=rs.getInt(1);
			}if(addressId>0)
			{
				flag=true;
				System.out.println("Record inserted into Address with primary key:"+addressId);
			}else
			{
				System.out.println("Could not insert record:");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(preSts!=null)
			{
				try {
					preSts.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		return addressId;
	}	
	
	@Override
	public boolean updateAddress(Address address, int addressId) {
		
		boolean flag=true;
		Connection conn=null;
		PreparedStatement preSts=null;
		 try {
			conn=DBConnection.getDbConnection();
			preSts=conn.prepareStatement(UPDATE_ADDRESS);
			int pos=0;
			preSts.setString(++pos, address.getLandMark());
			preSts.setInt(++pos,address.getAddressId());
			int result=preSts.executeUpdate();
			if(result>0)
			{
				flag=true;
				System.out.println(+result+" row updated:");
			}else
			{
				System.out.println("Not applicable to this address:");
			}
			 
			 
		} catch (Exception e) {
		  e.printStackTrace();
		}finally{
			
			
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(preSts!=null)
			{
				try {
					preSts.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}
	}
	
