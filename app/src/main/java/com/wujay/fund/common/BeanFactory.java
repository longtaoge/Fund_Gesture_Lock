package com.wujay.fund.common;

import java.io.IOException;
import java.util.Properties;

/**
 * 工厂类
 * 
 * @author Administrator
 * 
 */
public class BeanFactory {
	private static Properties properties;
	static {
		properties = new Properties();
		try {
			properties.load(BeanFactory.class.getClassLoader()
					.getResourceAsStream("classmap.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载需要的类
	 * 
	 * @param clazz
	 * @return
	 */

	public static <T> T getImpl(Class<T> clazz) {
		String key = clazz.getSimpleName();
		LogUtil.i(BeanFactory.class.getSimpleName(), key);
		String className = properties.getProperty(key);
		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Class<?> getImpl(String clazz) {
		try {
			return   Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
