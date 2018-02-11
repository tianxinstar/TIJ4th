/**
 * feiniu.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package tian.utils;

import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import tian.utils.pojo.Result;

/**
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2016年11月25日 上午10:03:40 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
public class APISupport {

	// rest类型 begin
	public static final String PUT = "PUT";
	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String DELETE = "DELETE";

	private static final Logger log = LoggerFactory.getLogger(APISupport.class);

	/**
	 * custom getResultStr
	 * 
	 * @param dataMapImpl
	 * @param apiUrl
	 * @param type
	 * @return
	 */
	public String getResultStr(JerseyClientUtils client, MultivaluedMap<String, String> dataMapImpl, String apiUrl,
			String type) {
		StopWatch watch = new StopWatch();
		watch.start();
		Long watchTime = 0l;
		String resultStr = null;
		if (StringUtils.isBlank(type))
			type = POST;
		if (PUT.equals(type))
			resultStr = client.put(apiUrl, dataMapImpl);
		else if (DELETE.equals(type))
			resultStr = client.delete(apiUrl, dataMapImpl);
		else if (GET.equals(type))
			resultStr = client.get(apiUrl);
		else
			resultStr = client.post(apiUrl, dataMapImpl);
		watch.stop();
		watchTime = watch.getTime();
		String logMsg = "api执行时间 " + apiUrl + ", execute time:" + watchTime + "ms";
		if (watchTime > 5000) {// 5 seconds
			log.error(logMsg);
		} else {
			log.info(logMsg);
		}
		return resultStr;
	}

	protected Result doPostCall(JerseyClientUtils client, MultivaluedMap<String, String> param, String apiUrl) {
		try {
			/* 记录调用url及调用参数 */
			Set<String> keySet = param.keySet();
			log.info("开始调用远程API :" + apiUrl);
			StringBuilder strPa = new StringBuilder("本次调用请求参数如下:");
			for (String key : keySet) {
				strPa.append(key).append("=").append(param.get(key));
			}
			log.info(strPa.toString());

			/* 开始POST调用 */
			String resultStr = client.post(apiUrl, param);
			log.info("本次调用[" + apiUrl + "]返回结果:" + resultStr);
			Result response = JSON.parseObject(resultStr, Result.class);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
