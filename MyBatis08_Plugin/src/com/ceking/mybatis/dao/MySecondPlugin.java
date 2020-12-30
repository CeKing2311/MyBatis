package com.ceking.mybatis.dao;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * 完成插件的签名：
 *	  表示当前插件用来拦截哪个对象的哪个方法
 *
 */
@Intercepts({
	@Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)	
})
public class MySecondPlugin implements Interceptor {

	/**
	 * intercept :拦截，拦截目标对象的目标方法的执行
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//执行目标方法
		System.out.println("MySecondPlugin intercept……"+invocation.getMethod());
		Object object = invocation.proceed();		
		return object;
	}
	/**
	 * plugin：包装目标对象，为目标对象创建一个代理对象
	 */
	@Override
	public Object plugin(Object target) {		
		//return Interceptor.super.plugin(target);
		System.out.println("MySecondPlugin plugin……将要包装的对象："+target);
		Object wrap = Plugin.wrap(target, this);
		//
		return wrap;
	}
	/**
	 * setProperties ：将插件注册时的属性方法注册进来
	 */
	@Override
	public void setProperties(Properties properties) {
		//
		System.out.println("MySecondPlugin setProperties……："+properties);
		Interceptor.super.setProperties(properties);
	}
}
