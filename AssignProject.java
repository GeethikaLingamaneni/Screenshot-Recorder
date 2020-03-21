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
 * Servlet implementation class AssignProject
 */
@WebServlet("/AssignProject")
public class AssignProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		Statement st=null;
		ResultSet rs=null;
		String projectname=request.getParameter("projectname");
		String manager=request.getParameter("manager");
		String member=request.getParameter("member");
		
		con=databasecon.getconnection();
		
		try {
			System.out.println("update employee set project='"+projectname+"' where empname='"+manager+"' OR empname='"+member+"'");
			ps=con.prepareStatement("update employee set project='"+projectname+"' where empname='"+manager+"' OR empname='"+member+"'");
			int j=ps.executeUpdate();
			if (j>0) {
				response.sendRedirect("AdminHome.jsp?Projectassigned Successfully");
			} else {
				response.sendRedirect("AdminHome.jsp?Projectassigned  fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}


