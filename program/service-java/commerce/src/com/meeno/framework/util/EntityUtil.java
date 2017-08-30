package com.meeno.framework.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

import com.meeno.framework.bean.BaseEntity;
import com.meeno.framework.exception.BusinessRuntimeException;


public class EntityUtil {
	public static Object getClassTypeValue(String value,Class<?> valueType){
		if(valueType.equals(String.class)){
			return value;
		}
		if(valueType.equals(int.class)||valueType.equals(Integer.class)){
			return Integer.valueOf(value);
		}
		if(valueType.equals(long.class)||valueType.equals(Long.class)){
			return Long.valueOf(value);
		}
		if(BaseEntity.class.isAssignableFrom(valueType)){
			BaseEntity object;
            try {
                object = (BaseEntity)valueType.newInstance();
            } catch (Exception e) {
                throw new BusinessRuntimeException(e.getMessage(),e);
            }
			object.setId(Long.valueOf(value));
			return object;
		}
		if(valueType.equals(java.util.Date.class)){
			return CommonUtil.getDateValue(value);
		}
		if(valueType.equals(java.sql.Date.class)){
			return java.sql.Date.valueOf(value);
		}
		if(valueType.equals(double.class)||valueType.equals(Double.class)){
			return Double.valueOf(value);
		}
		if(valueType.equals(float.class)||valueType.equals(Float.class)){
			return Float.valueOf(value);
		}
		/*if(valueType.equals(short.class)||valueType.equals(Short.class)){
			return Short.valueOf(value);
		}*/
		if(valueType.equals(boolean.class)||valueType.equals(Boolean.class)){
			return Boolean.valueOf(value);
		}
		if(valueType.equals(BigInteger.class)){
			return new BigInteger(value);
		}
		if(valueType.equals(BigDecimal.class)){
			return new BigDecimal(value);
		}
		if(valueType.equals(char.class)){
			return value.charAt(0);
		}
		if(valueType.equals(byte.class)||valueType.equals(Byte.class)){
			return Byte.valueOf(value);
		}
		throw new BusinessRuntimeException("Can not find a suitability data type for the type "+valueType);
	}

	public static Class<?> getClassPropertyType(Class<?> myClass,String propertyName){
		String methodName = "get"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
		try {
            return myClass.getMethod(methodName).getReturnType();
        } catch (Exception e) {
            throw new BusinessRuntimeException(e.getMessage(),e);
        }
	}
	public static Object getPropertyValue(final Class<?> myclass,final String inputProperty,final String value) {
		String property = inputProperty.substring(inputProperty.indexOf(".")+1);
		String properties[] = property.split("\\.");
		Class<?> propertyClass = null;
		for(int i=0;i<properties.length;i++){
			if(i==0){
				propertyClass = getClassPropertyType(myclass,properties[0]);
			}else{
				propertyClass = getClassPropertyType(propertyClass,properties[i]);
			}
		}
		return getClassTypeValue(value,propertyClass);
	}

	public static Object getPropertyValue(HashMap<String,Class<?>> entityClassMap,final String inputProperty, final String value) {
		String classAliaName = inputProperty.substring(0,inputProperty.indexOf("."));
		String property = inputProperty.substring(inputProperty.indexOf(".")+1);
		String properties[] = property.split("\\.");
		Class<?> propertyClass = null;
		for(int i=0;i<properties.length;i++){
			if(i==0){
				propertyClass = getClassPropertyType(entityClassMap.get(classAliaName),properties[0]);
			}else{
				propertyClass = getClassPropertyType(propertyClass,properties[i]);
			}
		}
		return getClassTypeValue(value,propertyClass);
	}
}
