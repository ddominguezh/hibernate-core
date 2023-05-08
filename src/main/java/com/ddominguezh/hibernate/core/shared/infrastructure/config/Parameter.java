package com.ddominguezh.hibernate.core.shared.infrastructure.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public final class Parameter {
	
    private final Properties properties;

    public Parameter() {
        this.properties = new Properties();
    }
    
    public void load(String filepath) {
    	try {
			this.properties.load(this.getClass().getResourceAsStream("/" + filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public String get(String key) throws ParameterNotExist {
        String value = this.properties.get(key).toString();

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
