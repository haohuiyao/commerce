package com.meeno.framework.eventbus;

import com.google.common.eventbus.EventBus;

import sun.security.jca.GetInstance;

public class MNEventBus {


	private static MNEventBus instance;
	private EventBus eventBus;
	
	public static MNEventBus getInstance(){
		if (instance == null) {
			instance = new MNEventBus();
		}
		return instance;
	}
	
	
	
	public MNEventBus() {
		super();
		eventBus = new EventBus();
	}

	public void post(Object event) {
		eventBus.post(event);
	}

	public void register(Object handler) {
		eventBus.register(handler);
	}

	public void unregister(Object handler) {
		eventBus.unregister(handler);
	}
}
