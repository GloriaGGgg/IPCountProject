package com.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 这是统计用户访问网站的ip流量例子
 * 
 * 从application中获取Map
 * 从request中得到当前客户端的ip
 * 进行统计工作，把结果放在Map中
 */
/**
 * this filter is to filter everything
 * @author Gloria
 *
 */
public class AFilter implements Filter {
	private FilterConfig filterConfig;//用来得到servletContext
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1.得到application中map
		 * 2.从request中获取当前客户端的ip
		 * 3.查看map中是否存在当前ip。如果存在，count数+1再保存回去
		 * 4.如果不存在，则设置访问次数为1
		 */
		
		
		/*
		 * 1.得到application中的map
		 */
		//得到servletContext中application
		ServletContext app=filterConfig.getServletContext();
		//通过application得到map
		Map<String, Integer> map=(Map<String, Integer>) app.getAttribute("map");
		
		
		/*
		 * 2.从request中获取当前ip
		 */
		String ip=request.getRemoteAddr();
		
		
		/*
		 * 3.查看map中是否有当前ip，进行判断
		 */
		//如果存在，说明不是第一次访问，在原来count基础上+1，再保存回去
		if(map.containsKey(ip)){
			int count=map.get(ip);//通过key得到对应的值,即多少数量
			map.put(ip, count+1);//然后把ip对应的值+1保存回去
		}
		else//如果不存在，说明时第一次访问，那么在对应的值后面设置为1
		{
			map.put(ip, 1);
		}
		
		
		/*
		 * 4.保存修改的信息回去
		 */
		app.setAttribute("map", map);
		
		//统计ip的访问次数，不需要拦截，一直放行就行
		chain.doFilter(request, response);
	}

	
	/**
	 * 在服务器启动的时候就产生
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig=fConfig;//因为这个可以得到application
	}

}
