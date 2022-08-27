package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FieldFupUserDao;

public class AuthorAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer userID = Integer.parseInt(req.getParameter("userID"));
			String[] fields = req.getParameterValues("field");
			
			FieldFupUserDao ffud = new FieldFupUserDao();
			ffud.deleteAuthor(userID);
			ffud.addAuthor(fields, userID);
			
			resp.sendRedirect("authorizationPage.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	
	}
}
