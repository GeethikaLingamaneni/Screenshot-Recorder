import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminAction
 */
@WebServlet("/AdminAction")
public class AdminAction extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

String Username=request.getParameter("UserName");
System.out.println("Username is"+Username);

String Pwd=request.getParameter("password");
System.out.println("Password is"+Pwd);
if (Username.equals("admin")&&Pwd.equals("admin")) {
	response.sendRedirect("AdminHome.jsp");
	
} else {

	response.sendRedirect("index.html");
}
	
	}}
