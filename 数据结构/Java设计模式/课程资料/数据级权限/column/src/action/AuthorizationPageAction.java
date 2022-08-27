package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**用户授权页面*/
public class AuthorizationPageAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			UserDao ud = new UserDao();
			List<User> users = ud.getUsers();
			
			req.getSession().setAttribute("usersList", users);
			resp.sendRedirect("users.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
