package com.web.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ���������������Map��������¼ip�ͷ��ʴ���
 * @author Gloria
 *
 */
public class AListener implements ServletContextListener {
	/**
	 * �ڷ���������ʱ����Map�������浽servletContext��
	 */
    public void contextInitialized(ServletContextEvent arg0) {
    	//����map
       Map<String, Integer> map=new LinkedHashMap<String,Integer>();
       //�õ�servletContext
       ServletContext application=arg0.getServletContext();
       //��map���浽application��
       application.setAttribute("map", map);
    }


    public void contextDestroyed(ServletContextEvent arg0) {
      
    }
	
}
