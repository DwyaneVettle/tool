package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FieldFupUserDao;
import dao.UserDao;
import model.FieldFupUser;
import model.User;

public class LoginAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		UserDao ud = new UserDao();
		try {
			User user = ud.queryUser(name);
			if( user == null ){
				System.out.println("用户名错误!");
				return;
			}
			//获取用户列级信息
			FieldFupUserDao ffud = new FieldFupUserDao();
			List<FieldFupUser> columnsList = ffud.queryColumns(user.getId());
			
			
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", user);
			session.setAttribute("field_cup_user", columnsList);
			
			resp.sendRedirect("index.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
