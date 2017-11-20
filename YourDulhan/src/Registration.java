

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet 
{
	
	public static final String driverName="com.mysql.jdbc.Driver";
	public static final String dbUrl="jdbc:mysql://localhost:3306/your_dulhan";
	public static final String dbUser="root";
	public static final String dbPassword="root";
	
	
	public static String INSERT_REGISTRATION="insert into register(first_name,last_name,email,mobile_number)values(?,?,?,?)";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		int id=0;
		
		
		
		
		String firstName=request.getParameter("fName");  
		String lastName=request.getParameter("lName");  
		String email=request.getParameter("email");  
		String mobNumber=request.getParameter("mobNumber"); 
		
		
		try {
			Class.forName(driverName);
			Connection conn=DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			conn.setAutoCommit(false);
			int pos=0;
			ResultSet rs=null;
			PreparedStatement preSts=conn.prepareStatement(INSERT_REGISTRATION, PreparedStatement.RETURN_GENERATED_KEYS);
			preSts.setString(++pos, firstName);
			preSts.setString(++pos, lastName);
			preSts.setString(++pos, email);
			preSts.setString(++pos, mobNumber);
			preSts.executeUpdate();
			rs=preSts.getGeneratedKeys();
			while(rs.next())
			{
				conn.commit();
				id=rs.getInt(1);
				System.out.println("Record inserted");
			}	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
