package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;

public class EmpListPageAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			EmployeeDao ed = new EmployeeDao();
			
			req.getSession().setAttribute("empList", ed.queryAllEmp());
			resp.sendRedirect("users.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
