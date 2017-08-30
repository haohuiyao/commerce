package com.meeno.framework.tag;

public class CommonBean implements java.io.Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -991745601454621167L;
	String name;
	String value;
	String fieldName;

	public String getName() {
		return name;
	}
	/*public void setName(String name) {
		this.name = name;
	}*/
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public CommonBean(String name,String value){
		this.name=name;
		this.fieldName = name;
		this.value = value;
	}
	public CommonBean(String name,String value,Object typeValue){
		this.name=name;
		this.fieldName = name;
		this.value = value;
		this.typeValue = typeValue;
	}
	Object typeValue;
	public Object getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(Object typeValue) {
		this.typeValue = typeValue;
	}
	String arithmeticOpertor;
	public String getArithmeticOpertor() {
		return arithmeticOpertor;
	}
	public void setArithmeticOpertor(String arithmeticOpertor) {
		this.arithmeticOpertor = arithmeticOpertor;
	}
	boolean isApplyLikeOperator;
	public boolean isApplyLikeOperator() {
		return isApplyLikeOperator;
	}
	public void setApplyLikeOperator(boolean isApplyLikeOperator) {
		this.isApplyLikeOperator = isApplyLikeOperator;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
