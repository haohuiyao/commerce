package com.meeno.framework.bean;

/**
 * Simple JavaBean domain object with an id property.
 * Used as a base class for objects needing this property.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class  BaseEntity implements java.io.Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -6834323741449129027L;

	private Long id;

	private String status;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public boolean isNew() {
		return (this.id == null);
	}

	public String toString() {
		return this.id==null?"Null":id.toString();
	}

	private boolean checked;


	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
