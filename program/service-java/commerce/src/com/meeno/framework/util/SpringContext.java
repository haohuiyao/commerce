package com.meeno.framework.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringContext implements ServletContextListener {

	 private  WebApplicationContext springContext;
	 public static  void setSpringContext(WebApplicationContext springContext) {
		 instance.springContext = springContext;
    }
	 public static SpringContext instance = new SpringContext();

    //public static  String APPLCATION_CONTEXT = "";
	 public void contextInitialized(ServletContextEvent event) {
		 instance.springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	        //APPLCATION_CONTEXT = event.getServletContext().getContextPath();
	    }

	    public void contextDestroyed(ServletContextEvent event) {
	    	instance.springContext = null;
	    	//APPLCATION_CONTEXT = null;
	    }

	    public static ApplicationContext getApplicationContext() {
	        return instance.springContext;
	    }

	    public static Object getBean(String beanName){
	    	return instance.springContext.getBean(beanName);
	    }
}
