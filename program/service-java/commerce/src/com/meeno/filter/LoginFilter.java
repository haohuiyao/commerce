package com.meeno.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.util.CommonUtil;
import com.meeno.framework.util.Constant;
import com.meeno.framework.util.MeenoSessionContext;

/**
 * 微信端登录状态验证
 * 
 * @author jeff_gao
 *
 */
public class LoginFilter implements Filter {

	public LoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 过滤器
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String sid = request.getParameter("Sid");
		HttpSession session = MeenoSessionContext.getInstance().getSession(sid);
		if (session == null) {
			session = request.getSession();
		}

		System.out.println("*******" + session.getId() + "******");
		if ((session.getAttribute("buyer") != null) || (session.getAttribute("baseUser") != null)) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			CommonUtil.toJson(response, new ResponseBean(Constant.RESPONSE_NOT_LOGGED, "未登录"));
		}

	}

	/**
	 * 初始化
	 * 
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}