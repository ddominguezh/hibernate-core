package com.ddominguezh.hibernate.core.shared.infrastructure.config;

public final class ParameterNotExist extends Throwable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5178642058101634498L;

	public ParameterNotExist(String key) {
        super(String.format("The parameter <%s> does not exist in the environment file", key));
    }
}
