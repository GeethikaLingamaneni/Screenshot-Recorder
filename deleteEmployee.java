import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseconnection.databasecon;

/**
 * Servlet implementation class deleteEmployee
 */
@WebServlet("/deleteEmployee")
public class deleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Connection con=null;
PreparedStatement pst=null;

	String Empid=request.getParameter("Eid");
	con=databasecon.getconnection();
	try {
		pst=con.prepareStatement("delete from employee where empid='"+Empid+"'");
		int i=pst.executeUpdate();
		if (i>0) {
			response.sendRedirect("AdminHome.jsp?deleteflag=Y");
		} else {
			response.sendRedirect("AdminHome.jsp?deleteflag=N");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
