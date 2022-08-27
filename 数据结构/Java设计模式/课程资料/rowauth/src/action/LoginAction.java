package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDao;
import dao.RowAuthDao;
import model.Employee;
import model.RowAuth;

public class LoginAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		try {
			EmployeeDao ed = new EmployeeDao();
			Employee emp = ed.queryEmp(name);
			if( emp == null ){
				System.out.println("用户名错误!");
				return;
			}
			//获取用户行级权限
			RowAuthDao rad = new RowAuthDao();
			String rowAuth = rad.queryRowAuth(emp.getId());
			
			
			HttpSession session = req.getSession();
			session.setAttribute("loginEmp", emp);
			session.setAttribute("empRowAuth", rowAuth);
			
			
			resp.sendRedirect("index.action");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
