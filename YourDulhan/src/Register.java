

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp.DriverManagerConnectionFactory;

import com.yourdulhan.util.DBConnection;


@WebServlet("/Register")
public class Register extends HttpServlet {
	public static final String driverName="com.mysql.jdbc.Driver";
	public static final String dbUrl="jdbc:mysql://localhost:3306/your_dulhan";
	public static final String dbUser="root";
	public static final String dbPassword="root";


	public static final String INSERT_REGISTER="insert into register(first_name,last_name)values(?,?)";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection conn=null;
		ResultSet rs=null;
		Boolean flag=false;
		PreparedStatement preSts=null;
		int id=0;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String firstName=request.getParameter("fName");
		String lastName=request.getParameter("lName");
		/*String lastName=request.getParameter("lName");
		String dob=request.getParameter("dob");
		String address=request.getParameter("address");
		String mobileNumber=request.getParameter("mobileNumber");
		String aadharNumber=request.getParameter("aadharNumber");
		String gender=request.getParameter("gender");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String status=request.getParameter("status");*/
		
		
		
		try {
			Class.forName(driverName);
			conn=DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			//conn=DBConnection.getDbConnection();
			preSts=conn.prepareStatement(INSERT_REGISTER);
			int pos=0;
			preSts.setString(++pos,firstName);
			preSts.setString(++pos, lastName);
			preSts.executeUpdate();
			rs=preSts.getGeneratedKeys();
			if(rs.next())
			{
				conn.commit();
				id=rs.getInt(1);
				response.sendRedirect("Welcome.jsp");
			}else
			{
				System.out.println("Register again");
			}		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
