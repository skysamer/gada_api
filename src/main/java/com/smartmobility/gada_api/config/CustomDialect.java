package com.smartmobility.gada_api.config;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomDialect extends MySQL5Dialect {

    public CustomDialect() {
        super();
        registerFunction("match",
                new SQLFunctionTemplate(StandardBasicTypes.DOUBLE,"match(?1) against (?2 in boolean mode)"));
    }
}
