package com.meeno.springframework.view.extn;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class JstlViewResolver implements ViewResolver {
	private static final Logger LOGGER = Logger.getLogger(MyViewResolver.class);

	private ViewResolver tilesResolver;
    private ViewResolver jspResolver;

	public void setJspResolver(ViewResolver jspResolver) {
        this.jspResolver = jspResolver;
    }

    public void setTilesResolver(ViewResolver tilesResolver) {
        this.tilesResolver = tilesResolver;
    }

	public View resolveViewName(String viewName, Locale locale) throws Exception {
		if(isJspView(viewName)){
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("当前页面："+viewName);
			}
			return jspResolver.resolveViewName(viewName, locale);
		}
		return tilesResolver.resolveViewName(viewName, locale);
	}
	private boolean isJspView(String viewName){
		return viewName.endsWith(".jsp");
	}

}
