package com.meeno.framework.bean;

/**
 * 输出json数组对象
 * @author jeff_gao
 * @Comment Beans
 */
public class ResponseBean {
	
	/**
	 * 标识  //0成功  1失败
	 */
	private Integer code;
	/**
	 * 提示信息
	 */
	private  String message;
	
	/**
	 * 数据
	 */
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResponseBean() {
		super();
	}
	
	public ResponseBean(Integer code, String message){
		this(code, message, "");
	}

	public ResponseBean(Integer code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
