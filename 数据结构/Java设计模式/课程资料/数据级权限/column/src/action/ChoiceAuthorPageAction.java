package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FieldFupUserDao;
import model.FieldFupUser;

/**授权选择页面*/
public class ChoiceAuthorPageAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer userID = Integer.parseInt( req.getParameter("userID") );
			
			FieldFupUserDao ffud = new FieldFupUserDao();
			List<FieldFupUser> list = ffud.queryUserAuthor(userID);
			
			
			
			req.setAttribute("authorList", list);
			req.setAttribute("userID", userID);
			req.getRequestDispatcher("authorize.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
}
