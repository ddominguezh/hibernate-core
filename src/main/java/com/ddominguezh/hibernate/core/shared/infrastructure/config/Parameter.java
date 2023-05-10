package com.ddominguezh.hibernate.core.shared.infrastructure.config;

public final class Parameter {

    public String get(String key) throws ParameterNotExist {
        String value = System.getenv(key).toString();

        if (null == value) {
            throw new ParameterNotExist(key);
        }

        return value;
    }

    public Integer getInt(String key) throws ParameterNotExist {
        String value = get(key);

        return Integer.parseInt(value);
    }

	
}
