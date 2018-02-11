/**
 * feiniu.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package tian.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2017年12月18日 下午3:27:46 <br>
 * 
 * vm args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * java堆内存溢出， 堆内存最小20m，最大20m； 后面参数：内存溢出保存快照方便事后分析
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
class OOMObject {

}

public class HeapOOM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while (true) {
			list.add(new OOMObject());
		}

	}

}
/**
 java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid13992.hprof ...
Heap dump file created [28014856 bytes in 0.075 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at tian.jvm.HeapOOM.main(HeapOOM.java:31)
ERROR: JDWP Unable to get JNI 1.2 environment, jvm->GetEnv() return code = -2
JDWP exit error AGENT_ERROR_NO_JNI_ENV(183):  [util.c:840]
 */
