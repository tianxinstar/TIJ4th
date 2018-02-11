// Enum singleton - the preferred approach - page 18
package org.effectivejava.examples.chapter02.item03.enumoration;

/**
 * <B>Description:</B> 单例模式，提供序列号机制，绝对防止多次实例化，单元素的枚举值是实现单例模式的最佳方法 <br>
 * <B>Create on:</B> 2018年1月3日 上午10:39:05 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
public enum Elvis {
	INSTANCE;

	public void leaveTheBuilding() {
		System.out.println("Whoa baby, I'm outta here!");
	}

	// This code would normally appear outside the class!
	public static void main(String[] args) {
		Elvis elvis = Elvis.INSTANCE;
		elvis.leaveTheBuilding();
	}
}
