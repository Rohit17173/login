package login.org;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginApp")
public class LoginApp extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("name1");
		String password=request.getParameter("pass");
		try {
		com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(d);
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/oct", "root", "root");
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery("select username,password from servregister where username='"+username+"' and password='"+password+"' ");
		if(rs.next())
		{
			RequestDispatcher r=request.getRequestDispatcher("welcome.html");
			r.forward(request, response);
			
		}else
		{
			RequestDispatcher r=request.getRequestDispatcher("login.html");
			r.include(request,response);
			out.println("<h2>Try Again...invalid username and password");
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
