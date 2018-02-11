/**
 * feiniu.com Inc.
 * Copyright (c) All Rights Reserved.
 */
package java8.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <B>Description:</B> TODO <br>
 * <B>Create on:</B> 2017年12月15日 下午5:24:00 <br>
 *
 * @author xinxin.tian(tianxin_star@163.com)
 * @version 1.0
 */
public class LambdaTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 1. thread
		// Java 8之前：
		new Thread(new Runnable() {
			@Override
			public void run() {
				printDelay("Before Java8, too much code for too little to do");
			}
		}).start();
		// Java 8之后：
		new Thread(() -> printDelay("lambda start thread， easy!!!")).start();
		// 2. list
		// Java 8之前：
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		for (String feature : features) {
			System.out.println(feature);
		}
		System.out.println();
		// Java 8之后：
		features.forEach(o -> System.out.println(o.toString()));
		System.out.println();

		// 3. stream
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
		System.out.println("Languages which starts with J :");
		filter(languages, (obj) -> ((String) obj).startsWith("J"));
		filterLambda(languages, (obj) -> ((String) obj).startsWith("J"));

		System.out.println("Languages which ends with a ");
		filter(languages, (str) -> ((String) str).endsWith("a"));
		filterLambda(languages, (str) -> ((String) str).endsWith("a"));

		System.out.println("Print all languages :");
		filter(languages, (str) -> true);
		filterLambda(languages, (str) -> true);

		System.out.println("Print no language : ");
		filter(languages, (str) -> false);
		filterLambda(languages, (str) -> false);

		System.out.println("Print language whose length greater than 4:");
		filter(languages, (str) -> ((String) str).length() > 4);
		filterLambda(languages, (str) -> ((String) str).length() > 4);
		System.out.println();

		// 4. 如何在lambda表达式中加入Predicate
		// 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
		// 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
		Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		Predicate<String> fourLetterLong = (n) -> n.length() == 4;
		languages.stream().filter(startsWithJ.and(fourLetterLong))
				.forEach((n) -> System.out.println("Name, which starts with 'J' and four letter long is : " + n));
		System.out.println();

		// 5.1 Java 8中使用lambda表达式的Map和Reduce示例
		// 不使用lambda表达式为每个订单加上12%的税
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			System.out.println(price);
		}
		System.out.println();
		// 使用lambda表达式
		costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
		System.out.println();

		// 5.2 Java 8中使用lambda表达式的Map和Reduce示例
		// 为每个订单加上12%的税
		// 老方法：
		costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double total = 0;
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			total = total + price;
		}
		System.out.println("Total : " + total);
		System.out.println();
		// 新方法：
		costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);
		System.out.println();

		// 6.通过过滤创建一个String列表
		// 创建一个字符串列表，每个字符串长度大于2
		List<String> filtered = languages.stream().filter(x -> x.length() > 3).collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n%n", languages, filtered);

		// 7.对列表的每个元素应用函数
		// 将字符串换成大写并用逗号链接起来
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
		System.out.println();

		// 8. 复制不同的值，创建一个子列表
		// 用所有不同的数字创建一个正方形列表
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
		System.out.printf("Original List : %s,  Square Without duplicates : %s %n%n", numbers, distinct);

		// 9.计算集合元素的最大值、最小值、总和以及平均值
		// 获取数字的个数、最小值、最大值、总和以及平均值
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : " + stats.getAverage());
	}

	static void printDelay(String msg) {
		try {
			TimeUnit.MILLISECONDS.sleep(200);
			System.out.println(msg);
			System.out.println();
		} catch (InterruptedException e) {
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void filter(List<String> names, Predicate condition) {
		for (String name : names) {
			if (condition.test(name)) {
				System.out.println(name + " ");
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void filterLambda(List<String> names, Predicate condition) {
		names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
			System.out.println(name + " ");
		});
	}

}
