package com.network.bird.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BeanFactory implements ApplicationContextAware {
	private ApplicationContext applicationContext;
 
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
	
	/**
	 * 
	 * @return 获得spring的上下文
	 */
	public ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	/**
	 * 根据bean的名称获得bean的实例
	 * @param name 名称
	 * @param requiredType bean的类型
	 * @return bean的实例
	 */
	public <T> T getBean(String name,Class<T> requiredType){
		return applicationContext.getBean(name,requiredType);
	}
	
	/**
	 * 根据bean的类型获得bean的实例
	 * @param type bean的类型，可以是超类或者接口的类型
	 * @return bean的实例
	 */
	public <T> Map<String,T> getBeansOfType(Class<T> type){
		return applicationContext.getBeansOfType(type);
	}

}
