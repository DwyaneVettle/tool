package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RowAuthDao;
import model.RowAuth;

/**选择权限页面*/
public class AuthorPageAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer empID = Integer.parseInt(req.getParameter("empID"));
			RowAuthDao rad = new RowAuthDao();
			List<RowAuth> empAuth = rad.queryEmpAuth(empID);
			
			req.setAttribute("empID", empID);
			req.setAttribute("empAuthList", empAuth);
			req.getRequestDispatcher("authorize.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
