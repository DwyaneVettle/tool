package action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CupDao;
import model.Cup;
import model.FieldFupUser;

public class IndexAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			List<FieldFupUser> columnsList = (List<FieldFupUser>) session.getAttribute("field_cup_user");
			
			CupDao cd = new CupDao();
			List<Cup> cupList = cd.queryColumnList( columnsList );
			
			
			req.setAttribute("cupList", cupList);
			req.setAttribute("columnsList", columnsList);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
}
