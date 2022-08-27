package com.woniuxy.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woniuxy.model.Func;

public class FuncFilter implements Filter{
	private List<String> noFilter;
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();
		
		//获取用户请求
		String reqUrl = uri.replaceAll(req.getContextPath(), "");
		if( noFilter.contains(reqUrl) ){//不需要过滤的请求
			chain.doFilter(req, resp);
			return;
		}
		
		
		//获取用户已有的权限
		List<Func> funcList = (List<Func>)req.getSession().getAttribute("funcList");
		//默认没有权限
		boolean isFunc = false;
		//注意：func.getUrl()有可能有多个url,如果有多个需要再次拆解
		for (Func func : funcList) {
			if( reqUrl.equals( func.getUrl() )){//改用户有权限
				isFunc = true;
				break;
			}
		}
		//用户没有权限
		if( !isFunc ){
			System.out.println("不要乱搞！！");
			return;
		}
		
		chain.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		noFilter = new ArrayList<String>();
		noFilter.add("/login.action");
	}

}
