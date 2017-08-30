package com.meeno.framework.util;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
public class MeenoSessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		MeenoSessionContext.getInstance().addSession(arg0.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		MeenoSessionContext.getInstance().delSession(arg0.getSession());
	}

}
