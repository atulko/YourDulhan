package com.yourdulhan.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.yourdulhan.dao.IMember;
import com.yourdulhan.pojo.Member;
import com.yourdulhan.util.DBConnection;

public class MemberImpl implements IMember{

	public static final String INSERT_MEMBER="insert into registration(first_name,last_name,email_id,mobile_number,is_active,password,gender,dob)values(?,?,?,?,?,?,?,?)";
	public static final String IS_MEMBER_EXIST="select * from registration where email_id=?";
	public static final String DISABLE_MEMBER="update registration set is_active=? where member_id=?";
	public static final String UPDATE_MEMBER="update registration set first_name=?,last_name=? where member_id=?";
	public static final String MEMBER_LIST="select * from registration";
	public static final String GET_MEMBER_DETAILS_BY_ID="select * from registration where member_id=?";




	@Override
	public boolean addMember(Member member) 
	{
		Connection conn=null;
		boolean flag=false;
		PreparedStatement preSts=null;
		ResultSet rs=null;
		int memberId=0;
		try {
			boolean result=MemberImpl.isMemberExist(member);
			if(result)
			{
				flag=false;
			}
			else
			{

				conn=DBConnection.getDbConnection();
				conn.setAutoCommit(false);
				preSts=conn.prepareStatement(INSERT_MEMBER,PreparedStatement.RETURN_GENERATED_KEYS);
				int pos=0;
				preSts.setString(++pos, member.getFirstName());
				preSts.setString(++pos, member.getLastName());
				preSts.setString(++pos,member.getEmailId());
				preSts.setString(++pos, member.getMobileNumber());
				preSts.setInt(++pos, member.getIsActive());
				preSts.setString(++pos, member.getPassword());
				preSts.setString(++pos, member.getGender());
				preSts.setString(++pos, member.getDob());
				preSts.executeUpdate();
				rs=preSts.getGeneratedKeys();
				if(rs.next())
				{
					conn.commit();
					memberId=rs.getInt(1);

				}if(memberId>0)
				{
					flag=true;
					System.out.println("Registration is successful");
				}else
				{
					System.out.println("Could not register");

				}


			}



		} catch (Exception e) {
			e.printStackTrace();

		}
		finally
		{
			if(conn!=null)
			{
				try {

					conn.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}if(preSts!=null)
			{
				try {

					preSts.close();

				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

		}
		return flag;
	}

	public static boolean isMemberExist(Member member)
	{
		boolean flag=false;
		Connection conn=null;
		PreparedStatement preSts=null;
		ResultSet rs=null;


		try {
			conn=DBConnection.getDbConnection();
			preSts=conn.prepareStatement(IS_MEMBER_EXIST);
			int pos=0;
			preSts.setString(++pos, member.getEmailId());
			rs=preSts.executeQuery();
			while(rs.next())
			{
				flag=true;
				System.out.println("You are already registered,Please sign in");
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



				} catch (Exception e3) {
					// TODO: handle exception
					e3.printStackTrace();
				}
			}
		}
		return flag;

	}


	@Override
	public boolean isActiveMember(int isActive, int memberId) 
	{
		boolean flag=false;
		Connection conn=null;
		PreparedStatement preSts=null;

		try {
			conn=DBConnection.getDbConnection();
			preSts=conn.prepareStatement(DISABLE_MEMBER);
			int pos=0;
			preSts.setInt(++pos, isActive);
			preSts.setInt(++pos, memberId);
			int result=preSts.executeUpdate();
			if(result>0)
			{
				flag=true;
				System.out.println("This member is no more longer");
			}else
			{
				System.out.println("This member still exist");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null)
			{
				try {

					conn.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
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

	@Override
	public boolean updateMember(Member member, int memberId)
	{
		boolean flag=false;
		Connection conn=null;
		PreparedStatement preSts=null;
		ResultSet rs=null;
		try {
			conn=DBConnection.getDbConnection();
			preSts=conn.prepareStatement(UPDATE_MEMBER);
			int pos=0;
			preSts.setString(++pos, member.getFirstName());
			preSts.setString(++pos, member.getLastName());
			preSts.setInt(++pos, memberId);
			int result=preSts.executeUpdate();
			if(result>0)
			{
				flag=true;
				System.out.println(member.getFirstName()+" :is set for member Id:"+memberId);
			}else
			{

				System.out.println("Failed to update member");
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

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
	public List<Member> getMemberList() 
	{
		List<Member> memberList=new ArrayList();
		Connection conn=null;
		PreparedStatement preSts=null;
		ResultSet rs=null;
		Member member=null;
		try {
			conn=DBConnection.getDbConnection();
			preSts=conn.prepareStatement(MEMBER_LIST);
			rs=preSts.executeQuery();
			while(rs.next())
			{

				member=new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setFirstName(rs.getString("first_name"));
				member.setLastName(rs.getString("last_name"));
				member.setEmailId(rs.getString("email_id"));
				member.setMobileNumber(rs.getString("mobile_number"));
				member.setIsActive(rs.getInt("is_active"));
				member.setPassword(rs.getString("password"));
				member.setGender(rs.getString("gender"));
				member.setDob(rs.getString("dob"));
				memberList.add(member);
				System.out.println(memberList);

			}		

		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			if(conn!=null)
			{
				try {
					conn.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}if(preSts!=null)
			{
				try {

					preSts.close();

				} catch (Exception e2) {

					e2.printStackTrace();

				}
			}

		}

		return memberList;
	}

	@Override
	public Member getMemberDetailsById(int memberId)
	{
		boolean flag=false;
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement preSts=null;
		Member member=null;
		try {
			conn=DBConnection.getDbConnection();
			preSts=conn.prepareStatement(GET_MEMBER_DETAILS_BY_ID);
			int pos=0;
			preSts.setInt(++pos, memberId);
			rs=preSts.executeQuery();
			while(rs.next())
			{

				member=new Member();
				member.setMemberId(rs.getInt("member_id"));
				member.setFirstName(rs.getString("first_name"));
				member.setLastName(rs.getString("last_name"));
				member.setEmailId(rs.getString("email_id"));
				member.setPassword(rs.getString("password"));
				member.setGender(rs.getString("gender"));
				member.setDob(rs.getString("dob"));
				member.setIsActive(rs.getInt("is_active"));
				member.setMobileNumber(rs.getString("mobile_number"));
				System.out.println("Details of member is:"+member);

			}			
		} catch (Exception e) {
			e.printStackTrace();			
		}finally{
			if(preSts!=null)
			{
				try {

					preSts.close();


				} catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}
			}if(conn!=null)
			{
				try {

					conn.close();


				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}



		return member;
	}



}
