package com.woniuxy.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniuxy.dao.FuncDao;
import com.woniuxy.dao.UserDao;
import com.woniuxy.model.Func;
import com.woniuxy.model.User;

public class LoginAction extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		UserDao ud = new UserDao();
		User user = ud.findUser(name);
		if( user == null ){
			System.out.println("用户不存在！");
			return;
		}
		
		//获取用户（角色）权限
		FuncDao fd = new FuncDao();
		List<Func> funcList = fd.findUserFunc(user.getId());
		
		req.getSession().setAttribute("funcList", funcList);
//		req.getSession().setAttribute("roleName", funcList.get(0));
		req.getSession().setAttribute("loginUser", user);
		resp.sendRedirect("index.jsp");
	}
}
