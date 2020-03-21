import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseconnection.databasecon;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps = null;
		Statement st=null;
		ResultSet rs=null;
		String Empname=request.getParameter("name");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String phonenumber=request.getParameter("phone");
		String Role=request.getParameter("Role");
		String Project="Not Assigned";
		con=databasecon.getconnection();
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("select max(empid) from employee");
			int i=0;
			while (rs.next()) {
				i=rs.getInt(1);
			}
			ps=con.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?)");
			ps.setInt(1,i+1);
			ps.setString(2, Empname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, email);
			ps.setString(6, phonenumber);
			ps.setString(7, Role);
			ps.setString(8, Project);
			int j=ps.executeUpdate();
			if (j>0) {
				response.sendRedirect("AdminHome.jsp?Registration Success");
			} else {
				response.sendRedirect("AdminHome.jsp?Registration fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}                                             

















