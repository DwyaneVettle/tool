package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConsumptionDao;
import model.Consumption;

public class IndexAction extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			String rowAuth = (String)session.getAttribute("empRowAuth");
			
			ConsumptionDao cd = new ConsumptionDao();
			List<Consumption> list = cd.query(rowAuth);
			
			
			req.setAttribute("cuspList", list);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
}
