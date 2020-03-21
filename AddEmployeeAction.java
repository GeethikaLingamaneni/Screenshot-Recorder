import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseconnection.databasecon;

@WebServlet("/AddEmployeeAction")
public class AddEmployeeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps = null;
		Statement st=null;
		ResultSet rs=null;
	//	String[] paramname = null;
		ArrayList<String> paramvalue = null;
		int i = 0;
		int maxid=0;
		Enumeration param = request.getParameterNames();
		PrintWriter out = response.getWriter();
		while (param.hasMoreElements()) {
			Object obj = param.nextElement();
			String fieldName = (String) obj;
			String fieldValue = request.getParameter(fieldName);
			System.out.println("field value is"+fieldValue);
			//paramname[i] = fieldName;
			//System.out.println("i value is"+i);
			try{
			paramvalue.add(i+1, fieldValue);
			}catch(Exception e){
				
			}
           i++;
		}
		 System.out.println("ArrayList is"+paramvalue.size());
try{
		for (int j = 0; j < paramvalue.size(); j++) {
			 con=databasecon.getconnection();
			 try {
				 if (j==0) {
					 st=con.createStatement();
				 rs=st.executeQuery("select max(id) from employee");			
						//Extact result from ResultSet rs
						while(rs.next()){
							maxid=rs.getInt(1);
						    //System.out.println("MAX(user_id)="+rs.getInt("MAX(user_id)"));				
						  }
					ps.setInt(i+1,maxid+1);
				}
				 else{
				ps.setString(i+1,paramvalue.get(j));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
catch(Exception e){
	e.printStackTrace();
}
		try {
			int updatecount=ps.executeUpdate();
			if (updatecount>0) {
				response.sendRedirect("AdminHome.jsp?Register Success");
				System.out.println("Register Successs");
			} else {
				response.sendRedirect("AdminHome.jsp?Register Fail");
             System.out.println("Register Failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}





















