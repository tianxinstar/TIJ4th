/**
 * feiniu.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package tian.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.Test;

/**
 * <B>Description:</B> 读取配置文件 <br>
 * <B>Create on:</B> 2017年12月11日 下午3:12:47 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
/*
 * @RunWith(SpringJUnit4ClassRunner.class)
 * 
 * @ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
 */
public class TextFileInputTest {
	private String json;

	@Before
	public void setUp() throws Exception {
		InputStream is = TextFileInputTest.class.getResourceAsStream("TextFileInputTest.json");
		try {
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			StringBuffer sb = new StringBuffer();
			char[] ch = new char[128];
			int len = 0;
			while ((len = isr.read(ch, 0, ch.length)) != -1) {
				sb.append(ch, 0, len);
			}
			json = sb.toString().trim();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				is.close();
		}
	}

	@Test
	public void test() {
		System.out.println(json);
	}

}
