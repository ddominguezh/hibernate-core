package com.ddominguezh.hibernate.core.shared.domain;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public abstract class IntValueObject implements Serializable {
    private int value;

    public IntValueObject(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "" + this.value();
    }

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntValueObject other = (IntValueObject) obj;
		return value == other.value;
	}
	
	public static int empty() {
		return Integer.MIN_VALUE;
	}
    
}
