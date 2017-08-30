package com.meeno.framework.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public final class MeenoSessionContext {
	private static MeenoSessionContext instance = new MeenoSessionContext();

	public static MeenoSessionContext getInstance() {
		return instance;
	}

	private MeenoSessionContext() {
		sessionMap = new HashMap<String, HttpSession>();
	}

	private Map<String, HttpSession> sessionMap;

	public synchronized void addSession(HttpSession session) {
		if (session != null) {
			sessionMap.put(session.getId(), session);
		}
	}

	public synchronized void delSession(HttpSession session) {
		if (session != null) {
			sessionMap.remove(session.getId());
		}
	}

	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null)
			return null;
		return (HttpSession) sessionMap.get(session_id);
	}
}
