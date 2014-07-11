package com.ehome.mobile.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.os.AsyncTask;

/**
 * Classe que faz a conexão com o web service (REST)
 * 
 * @author Silas M. Ferreira
 *
 */
public class RestClient<T> {
	
	private Logger logger = Logger.getLogger(RestClient.class);
	private ExecuteTask executeTask = new ExecuteTask();
	private List<HttpMessageConverter<?>> messageConverters;
	private RestTemplate restTemplate;
	private String url = "";
	private Class<T> clazz;
	private Object object;
	private T result;
	private AsyncTask<String, Void, T> asyncTask;	
	
	public RestClient() {
		try {
			this.restTemplate = new RestTemplate();
	        this.messageConverters = new ArrayList<HttpMessageConverter<?>>();
			this.messageConverters.add(new StringHttpMessageConverter());
			this.messageConverters.add(new GsonHttpMessageConverter());
			this.restTemplate.setMessageConverters(messageConverters);
	        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		} catch (Exception e) {
			logger.error("RestClient()", e);
		}
	}

	private class ExecuteTask extends AsyncTask<String, Void, T> {
		
		@Override
		protected T doInBackground(String... params) {
			try {
				if (params[0] == Constants.GET) {
					 return restTemplate.getForObject(url, clazz);
				} else {
					 return restTemplate.postForObject(url, object, clazz);
				}
			} catch (Exception e) {
				logger.error("doInBackground(String... params)", e);
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(T object) {
			result = object;
		}
	}
	
	public T sendGet(Class<T> clazz) {
		try {
			this.clazz = clazz;
			asyncTask = executeTask.execute(Constants.GET);
			result = asyncTask.get();			
		} catch (Exception e) {
			logger.error("sendGet(Class<T> clazz)", e);
		}
		return result;
	}
	
	public T sendPost(Class<T> clazz, Object object) {
		try {
			this.object = object;
			this.clazz = clazz;
			asyncTask = executeTask.execute(Constants.POST);
			result = asyncTask.get();
		} catch (Exception e) {
			logger.error("sendPost(Class<T> clazz, T object)", e);
		}
		return result;
	}
		
	public void setUrl(String url) {
		this.url = url;
	}
}