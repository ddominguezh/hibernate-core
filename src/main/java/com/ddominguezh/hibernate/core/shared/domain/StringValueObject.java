package com.ddominguezh.hibernate.core.shared.domain;

import java.io.Serializable;
import java.util.Objects;

public abstract class StringValueObject implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5080598818248142881L;
	private String value;

    public StringValueObject(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StringValueObject)) {
            return false;
        }
        StringValueObject that = (StringValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    public static String empty() {
    	return "";
    }
}
