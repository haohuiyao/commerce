package com.meeno.framework.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.meeno.framework.bean.ResponseBean;
import com.meeno.framework.exception.BusinessRuntimeException;

/**
 * 过滤器.
 *
 * @author winner_pan
 *
 */
public class CommonFilter implements Filter {
	/**
	 * 定义编码.
	 */
	protected String charset = "utf-8";
	protected boolean ignore = true;
	private FilterConfig fConfig;
	private static final String PARAM_PORTAL_LOCALE_LANGUAGE = "language";

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(final FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		this.charset = fConfig.getInitParameter("charset");
		String value = fConfig.getInitParameter("ignore");
		if (value == null) {
			this.ignore = true;
		}
		else if (value.equalsIgnoreCase("true")) {
			this.ignore = true;
		}
		else if (value.equalsIgnoreCase("yes")) {
			this.ignore = true;
		}
		else {
			this.ignore = false;
		}
		
	}

	/**
	 * 选择编码.
	 *
	 * @param request
	 *            ServletRequest
	 * @return 字符串
	 */
	protected String selectEncoding(final ServletRequest request) {
		return (this.charset);
	}

	/**
	 * 设置锁定cookie.
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	private void setLocalCookie(final HttpServletRequest request,
			final HttpServletResponse response) {
		String locale = request.getParameter(PARAM_PORTAL_LOCALE_LANGUAGE);
		CookieLocaleResolver resover = (CookieLocaleResolver) SpringContext
				.getBean("localeResolver");
		if (locale != null) {
			String[] localeInfo = locale.split("_");
			java.util.Locale newLocale = new java.util.Locale(localeInfo[0],
					localeInfo[1]);
			resover.setLocale(request, response, newLocale);
		}
		request.setAttribute(DispatcherServlet.LOCALE_RESOLVER_ATTRIBUTE,
				resover);
	}

	/**
	 * 过滤器.
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param chain
	 *            FilterChain
	 * @throws IOException
	 *             异常信息
	 * @throws ServletException
	 *             异常信息
	 */
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		// Conditionally select and set the character encoding to be used
		if (ignore || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request);
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
			}
		}
		setLocalCookie((HttpServletRequest) request,
				(HttpServletResponse) response);
		HttpServletRequest hRequest = (HttpServletRequest) request;
		
		// clear browse cache
		HttpServletResponse hResponse = (HttpServletResponse) response;
		hResponse.setHeader("Pragma", "No-Cache");
		hResponse.setHeader("Cache-Control", "No-Cache");
		hResponse.setDateHeader("Expires", 0);
		
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.toJson(hResponse, new ResponseBean(Constant.RESPONSE_ERROR, "系统异常"));
		}
		
	}

	public void destroy() {

	}
}
