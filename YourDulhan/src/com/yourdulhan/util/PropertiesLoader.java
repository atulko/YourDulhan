package com.yourdulhan.util;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;

public final class PropertiesLoader {

	private Properties _props = new Properties();

	private final String _propsFile;

	public String getPropsFile() {
		return _propsFile;
	}

	public PropertiesLoader(String propsFile) {
		_propsFile = propsFile;
		try {
			_props.load(getClass().getClassLoader().getResourceAsStream(_propsFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getValue(String key) {
		if (null == key)
			throw new NullPointerException("Key is null!");
		synchronized (_props) {
			return _props.getProperty(key);
		}
	}

	public String getValue(String key, String defaultValue) {
		if (null == key)
			throw new NullPointerException("Key is null!");
		String ret = null;
		synchronized (_props) {
			try {
				ret = _props.getProperty(key);
				return null == ret ? defaultValue : ret;
			} catch (MissingResourceException mre) {
				return defaultValue;
			}
		}
	}
}