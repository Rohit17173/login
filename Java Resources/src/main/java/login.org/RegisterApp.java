package login.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterApp")
public class RegisterApp extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
			com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(d);
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/oct", "root", "root");
			Statement stm=conn.createStatement();
		if(conn!=null)
		{
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String contact=request.getParameter("contact");
			String user=request.getParameter("us");
			String pass=request.getParameter("pas");
			PreparedStatement stmt=conn.prepareStatement("insert into servregister values('0',?,?,?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, contact);
			stmt.setString(4, user);
			stmt.setString(5, pass);
			int value=stmt.executeUpdate();
			if(value>0)
			{
				RequestDispatcher r=request.getRequestDispatcher("login.html");
				r.include(request, response);
				out.println("<h1>Register Sucessfully  login here.!!!<h1>");
			}
			else
			{
				
				RequestDispatcher r=request.getRequestDispatcher("register.html");
				out.println("Enter valid Details.....");
				r.include(request, response);
			}
		}else
		{
			out.println("Database is not conncetd");
		}
		}catch(Exception ex)
		{
			out.println("Error is "+ex);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
