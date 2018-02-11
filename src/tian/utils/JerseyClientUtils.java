/**
 * feiniu.com Inc.
 * Copyright (c) 2013-2014 All Rights Reserved.
 */
package tian.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

//@Component
public class JerseyClientUtils {

	private static final Logger log = LoggerFactory.getLogger(JerseyClientUtils.class);

	private Client client;

	@PostConstruct
	public void init() {
		StopWatch watch = new StopWatch();
		watch.start();
		client = Client.create();
		client.setConnectTimeout(3000);// 连接时间
		client.setReadTimeout(3000);// 读取数据时间
		watch.stop();
		log.info("###JerseyClientUtils-client init execute time:" + watch.getTime() + "ms " + client);
	}

	/**
	 * 关闭client
	 */
	@PreDestroy
	public void close() {
		client.destroy();
	}

	/**
	 * For repair data 生成代理client
	 * 
	 * @author xinxin.tian
	 * @param proxyHost
	 *            proxy.fn.com
	 * @param proxyPort
	 *            8080
	 */
	public void initProxy(final String proxyHost, final String proxyPort) {
		ClientConfig config = new DefaultClientConfig();
		client = new Client(new URLConnectionClientHandler(new HttpURLConnectionFactory() {
			Proxy p = null;

			@Override
			public HttpURLConnection getHttpURLConnection(URL url) throws IOException {
				if (p == null) {
					if (StringUtils.isNotEmpty(proxyHost)) {
						p = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
					} else {
						p = Proxy.NO_PROXY;
					}
				}
				return (HttpURLConnection) url.openConnection(p);
			}
		}), config);
	}

	/**
	 * POST方式调用API
	 * 
	 * @param url：API
	 *            URL地址
	 * @param formData：API参数
	 * @return
	 */
	public String post(String url, MultivaluedMap<String, String> formData) {
		StopWatch watch = new StopWatch();
		watch.start();
		WebResource resource = client.resource(url);
		watch.stop();
		log.debug("###JerseyClientUtils api执行时间 " + url + ", execute time:" + watch.getTime() + "ms"
				+ " client.resource");
		watch = new StopWatch();
		watch.start();
		ClientResponse response = null;
		try {
			response = resource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
			watch.stop();
			log.debug("###JerseyClientUtils api执行时间 " + url + ", execute time:" + watch.getTime() + "ms"
					+ " client.post");
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			return response.getEntity(String.class);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	public String put(String url, MultivaluedMap<String, String> formData) {
		WebResource resource = client.resource(url);
		ClientResponse response = null;
		try {
			response = resource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).put(ClientResponse.class, formData);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			return response.getEntity(String.class);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	public String delete(String url, MultivaluedMap<String, String> formData) {
		WebResource resource = client.resource(url);
		ClientResponse response = null;
		try {
			response = resource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).delete(ClientResponse.class, formData);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			return response.getEntity(String.class);
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

	/**
	 * GET方式访问URL，返回HTML内容
	 * 
	 * @param url
	 * @return
	 */
	public String get(String url) {
		WebResource resource = client.resource(url);
		ClientResponse response = null;
		try {
			response = resource.accept("*/*").get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String result = response.getEntity(String.class);
			return result;
		} finally {
			if (response != null) {
				response.close();
			}
		}
	}

}
