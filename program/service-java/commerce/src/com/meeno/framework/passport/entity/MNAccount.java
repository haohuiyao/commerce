package com.meeno.framework.passport.entity;

public class MNAccount {

	private static final long serialVersionUID = 1L;
	
	private String uuid;//
	private String status;
	
	public MNAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
