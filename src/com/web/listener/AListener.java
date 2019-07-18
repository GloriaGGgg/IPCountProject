package com.web.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 这个监听器里面存放Map，用来记录ip和访问次数
 * @author Gloria
 *
 */
public class AListener implements ServletContextListener {
	/**
	 * 在服务器启动时创建Map，并保存到servletContext中
	 */
    public void contextInitialized(ServletContextEvent arg0) {
    	//创建map
       Map<String, Integer> map=new LinkedHashMap<String,Integer>();
       //得到servletContext
       ServletContext application=arg0.getServletContext();
       //把map保存到application中
       application.setAttribute("map", map);
    }


    public void contextDestroyed(ServletContextEvent arg0) {
      
    }
	
}
