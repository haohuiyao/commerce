package com.meeno.framework.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class MysqlDialect extends MySQLDialect{

	public MysqlDialect() {  
        super();  
        registerFunction("date_add_interval", new SQLFunctionTemplate(Hibernate.STRING, "date_add(?1, INTERVAL ?2 ?3)"));  
    }
}
