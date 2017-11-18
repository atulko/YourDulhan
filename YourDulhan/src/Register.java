

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
 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register")
public class Register extends HttpServlet {
	public static final String driverName="com.mysql.jdbc.Driver";
	public static final String dbUrl="jdbc:mysql://localhost:3306/your_dulhan";
	public static final String dbUser="root";
	public static final String dbPassword="root";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String firstName=request.getParameter("fName");
		String middleName=request.getParameter("mName");
		String lastName=request.getParameter("lName");
		String dob=request.getParameter("dob");
		String address=request.getParameter("address");
		String mobileNumber=request.getParameter("mobileNumber");
		String aadharNumber=request.getParameter("aadharNumber");
		String gender=request.getParameter("gender");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String status=request.getParameter("status");
		

		try {
			Class.forName(driverName);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			PreparedStatement ps = con.prepareStatement("insert into registration(firstName,middleName,lastName,dob,address,mobileNumber,aadharNumber,gender,email,password,status) values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(2, firstName);
			ps.setString(3, middleName);
			ps.setString(4, lastName);
			ps.setString(5, dob);
			ps.setString(6, address);
			ps.setString(7, mobileNumber);
			ps.setString(8, aadharNumber);
			ps.setString(9, gender);
			ps.setString(10, email);
			ps.setString(11, password);
			ps.setString(12, status);
			
			int result = ps.executeUpdate();
			
			if(result>0){
				response.sendRedirect("Welcome.jsp");
			}
			else{
				response.sendRedirect("error.html");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		

		
	}

}
