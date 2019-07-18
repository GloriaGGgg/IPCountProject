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
 * ����ͳ���û�������վ��ip��������
 * 
 * ��application�л�ȡMap
 * ��request�еõ���ǰ�ͻ��˵�ip
 * ����ͳ�ƹ������ѽ������Map��
 */
/**
 * this filter is to filter everything
 * @author Gloria
 *
 */
public class AFilter implements Filter {
	private FilterConfig filterConfig;//�����õ�servletContext
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1.�õ�application��map
		 * 2.��request�л�ȡ��ǰ�ͻ��˵�ip
		 * 3.�鿴map���Ƿ���ڵ�ǰip��������ڣ�count��+1�ٱ����ȥ
		 * 4.��������ڣ������÷��ʴ���Ϊ1
		 */
		
		
		/*
		 * 1.�õ�application�е�map
		 */
		//�õ�servletContext��application
		ServletContext app=filterConfig.getServletContext();
		//ͨ��application�õ�map
		Map<String, Integer> map=(Map<String, Integer>) app.getAttribute("map");
		
		
		/*
		 * 2.��request�л�ȡ��ǰip
		 */
		String ip=request.getRemoteAddr();
		
		
		/*
		 * 3.�鿴map���Ƿ��е�ǰip�������ж�
		 */
		//������ڣ�˵�����ǵ�һ�η��ʣ���ԭ��count������+1���ٱ����ȥ
		if(map.containsKey(ip)){
			int count=map.get(ip);//ͨ��key�õ���Ӧ��ֵ,����������
			map.put(ip, count+1);//Ȼ���ip��Ӧ��ֵ+1�����ȥ
		}
		else//��������ڣ�˵��ʱ��һ�η��ʣ���ô�ڶ�Ӧ��ֵ��������Ϊ1
		{
			map.put(ip, 1);
		}
		
		
		/*
		 * 4.�����޸ĵ���Ϣ��ȥ
		 */
		app.setAttribute("map", map);
		
		//ͳ��ip�ķ��ʴ���������Ҫ���أ�һֱ���о���
		chain.doFilter(request, response);
	}

	
	/**
	 * �ڷ�����������ʱ��Ͳ���
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig=fConfig;//��Ϊ������Եõ�application
	}

}
