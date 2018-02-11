/**
 * feiniu.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package tian.io.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import tian.utils.APISupport;
import tian.utils.DateUtils;
import tian.utils.JerseyClientUtils;
import tian.utils.pojo.Result;

/**
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2018年1月4日 上午10:00:04 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
public class CsvInputTest extends APISupport {
	private static final Logger log = LoggerFactory.getLogger(CsvInputTest.class);

	/**
	 * 读取文件
	 * 
	 * @throws java.lang.Exception
	 */
	public List<String> inputData(String fileName) throws Exception {
		InputStream is = CsvInputTest.class.getResourceAsStream(fileName);
		List<String> allString = new ArrayList<>();
		try {
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String buffer = null;
			int i = 0;
			while ((buffer = br.readLine()) != null) {// 逐行读取数据
				log.info(buffer);
				if (i != 0)// 跳过第一行
					allString.add(buffer);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
		}
		return allString;
	}

	/**
	 * List<List<?>> 多行多列 List<?> 一行数据
	 * 
	 * @param fileName
	 *            /test.csv或者test.csv '/'表示根目录
	 * @throws Exception
	 */
	public void outData(List<List<?>> rows, String fileName) throws Exception {
		log.info("path1: {}", CsvInputTest.class.getResource("").toURI().getPath());
		// /D:/work/development/workspace/TIJ4th/bin/tian/io/csv/
		log.info("path2: {}", CsvInputTest.class.getResource("/").getPath());//
		// /D:/work/development/workspace/TIJ4th/bin/
		String filePath = CsvInputTest.class.getResource("").getPath() + fileName;
		log.info("path3: {}", filePath);
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			File file = new File(filePath);// 创建文件
			if (!file.exists())// 未找到文件，创建新文件
				file.createNewFile();
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out, "UTF8");
			bw = new BufferedWriter(osw);
			for (List<?> lines : rows) {
				bw.write(StringUtils.join(lines, ",") + "\r\n");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (bw != null)
				bw.close();
			if (osw != null)
				osw.close();
			if (out != null)
				out.close();
		}
	}

	/**
	 * 读取文件测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInput() throws Exception {
		List<String> allString = inputData("testInput.csv");
		log.info(allString.toString());
		log.info("size = {}", allString.size());
	}

	/**
	 * 写入文件测试 默认地址：TIJ4th/bin/tian/io/csv/testOutPut.csv
	 * 
	 * @throws Exception
	 */
	@Test
	public void testOutput() throws Exception {
		List<String> line1 = Arrays.asList("11");
		List<String> line2 = Arrays.asList("21", "22");
		List<String> line3 = Arrays.asList("31", "32", "33");
		List<List<?>> rows = Arrays.asList(line1, line2, line3);
		outData(rows, "testOutPut.csv");
	}

	/**
	 * 帐户余额导出
	 * @throws Exception
	 */
	@Test
	public void exportBalance() throws Exception {
		boolean test = false;
		List<String> allString = inputData("balance.csv");
		log.info("size = {}", allString.size());
		JerseyClientUtils client = new JerseyClientUtils();
		client.init();
		String url = "http://balance.idc1.fn/rest/balance_query/get_account";
		try {
			MultivaluedMap<String, String> dataMapImpl = null;
			Map<String, String> data = null;
			Set<String> errors = new HashSet<String>();
			List<List<?>> raws = new ArrayList<List<?>>();
			List<String> header = Arrays.asList("BM_MEM_GUID", "usableBalance", "freezeBalance");
			raws.add(header);
			int i = 0;
			for (String guid : allString) {
				if (test) {// 测试用，只取100条
					if (i == 100)
						break;
					i++;
				}
				data = new HashMap<String, String>();
				data.put("memGuid", guid);
				dataMapImpl = new MultivaluedMapImpl();
				dataMapImpl.add("data", JSON.toJSONString(data));
				Result result = doPostCall(client, dataMapImpl, url);
				if (result.getCode().equals("0")) {
					JSONObject json = JSONObject.parseObject(result.getData().toString());
					BigDecimal usableBalance = json.getBigDecimal("usableBalance");
					BigDecimal freezeBalance = json.getBigDecimal("freezeBalance");
					raws.add(Arrays.asList(guid, usableBalance.toString(), freezeBalance.toString()));
				} else {
					// error
					errors.add(guid);
				}
			}
			log.info("errors {}", errors);
			outData(raws, "balance_out_" + DateUtils.longDateTime(new Date()) + ".csv");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			client.close();
		}
	}

	/**
	 * 购物卡数据导出
	 * @throws Exception
	 */
	@Test
	public void exportBonus() throws Exception {
		List<String> allString = inputData("bonus.csv");
		String url = "http://balance.idc1.fn/rest/bonus/get_bonus2_point";
		boolean test = false;
		log.info("size = {}", allString.size());
		JerseyClientUtils client = new JerseyClientUtils();
		client.init();
		try {
			MultivaluedMap<String, String> dataMapImpl = null;
			Map<String, String> data = null;
			Set<String> errors = new HashSet<String>();
			List<List<?>> raws = new ArrayList<List<?>>();
			List<String> header = Arrays.asList("BM_MEM_GUID", "bonusPoints");
			raws.add(header);
			int i = 0;
			for (String guid : allString) {
				if (test) {// 测试用，只取100条
					if (i == 100)
						break;
					i++;
				}
				data = new HashMap<String, String>();
				data.put("memGuid", guid);
				dataMapImpl = new MultivaluedMapImpl();
				dataMapImpl.add("data", JSON.toJSONString(data));
				Result result = doPostCall(client, dataMapImpl, url);
				if (result.getCode().equals("0")) {
					raws.add(Arrays.asList(guid, result.getData()));
				} else {
					// error
					errors.add(guid);
				}
			}
			log.info("errors {}", errors);
			log.info("raws size {}", raws.size() - 1);
			outData(raws, "bonus_out_" + DateUtils.longDateTime(new Date()) + ".csv");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			client.close();
		}
	}

}
