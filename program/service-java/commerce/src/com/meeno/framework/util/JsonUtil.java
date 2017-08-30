package com.meeno.framework.util;

import com.meeno.framework.bean.BaseEntity;

public class JsonUtil {
	public static final void setFirstJsonElement(StringBuilder strBuilder,
			String name, Object value) {
		strBuilder.append("\"").append(name).append("\":");
		if (value == null) {
			strBuilder.append("null");
		} else if (value instanceof BaseEntity) {
			if (((BaseEntity) value).getId() == null) {
				strBuilder.append("null");
			} else {
				strBuilder.append("{\"id\":\"")
						.append(((BaseEntity) value).getId()).append("\"}");
			}
		} else {
			strBuilder.append("\"").append(value).append("\"");
		}
	}

	public static final void setNextJsonElement(StringBuilder strBuilder,
			String name, Object value) {
		strBuilder.append(",");
		setFirstJsonElement(strBuilder, name, value);
	}

	public static final void setObjectJsonElement(StringBuilder strBuilder,
			String name, Object value) {
		strBuilder.append(",\"").append(name).append("\":");
		if (value == null) {
			strBuilder.append("{\"id\":null}");
		} else {
			strBuilder.append("{\"id\":\"")
					.append(((BaseEntity) value).getId()).append("\"}");
		}
	}
}
