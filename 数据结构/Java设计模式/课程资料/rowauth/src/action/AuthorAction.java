package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RowAuthDao;

/**授权*/
public class AuthorAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Integer empID = Integer.parseInt(req.getParameter("empID"));
			String[] rowDatas = req.getParameterValues("rowDatas");
			
			for (String s : rowDatas) {
				System.out.println(s);
			}
			
			
			RowAuthDao rad = new RowAuthDao();
			rad.deleteAuthor(empID);
			rad.addAuthor(rowDatas, empID);
			
			resp.sendRedirect("empListPage.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
}
