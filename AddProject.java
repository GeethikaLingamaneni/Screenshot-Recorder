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
 * Servlet implementation class AddProject
 */
@WebServlet("/AddProject")
public class AddProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		Statement st=null;
		ResultSet rs=null;
		String projectname=request.getParameter("projectname");
		String clientname=request.getParameter("clientname");
		String duration=request.getParameter("duration");
		con=databasecon.getconnection();
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("select max(projectid) from projects");
			int i=0;
			while (rs.next()) {
				i=rs.getInt(1);
			}
			ps=con.prepareStatement("insert into projects values(?,?,?,?)");
			ps.setInt(1,i+1);
			ps.setString(2, projectname);
			ps.setString(3, clientname);
			ps.setString(4, duration);
			int j=ps.executeUpdate();
			if (j>0) {
				response.sendRedirect("AdminHome.jsp?Projectadded Successfully");
			} else {
				response.sendRedirect("AdminHome.jsp?Projectadded  fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}
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
 * Servlet implementation class AddProject
 */
@WebServlet("/AddProject")
public class AddProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		Statement st=null;
		ResultSet rs=null;
		String projectname=request.getParameter("projectname");
		String clientname=request.getParameter("clientname");
		String duration=request.getParameter("duration");
		con=databasecon.getconnection();
		
		try {
			st=con.createStatement();
			rs=st.executeQuery("select max(projectid) from projects");
			int i=0;
			while (rs.next()) {
				i=rs.getInt(1);
			}
			ps=con.prepareStatement("insert into projects values(?,?,?,?)");
			ps.setInt(1,i+1);
			ps.setString(2, projectname);
			ps.setString(3, clientname);
			ps.setString(4, duration);
			int j=ps.executeUpdate();
			if (j>0) {
				response.sendRedirect("AdminHome.jsp?Projectadded Successfully");
			} else {
				response.sendRedirect("AdminHome.jsp?Projectadded  fail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}}
