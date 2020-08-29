package com.yang.javabase.news;

import java.util.*;
import java.util.stream.Collectors;

/***************************************
 * @author:Alex Wang
 * @Date:2016/10/27 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class CollectorsAction {


    public  static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        testAveragingDouble();
        testAveragingInt();
        testAveragingLong();
        testCollectingAndThen();
        testCounting();
        testGroupingByFunction();
        testGroupingByFunctionAndCollector();
        testGroupingByFunctionAndSupplierAndCollector();
        testSummarizingInt();
    }

    /**
     * 求平均值（double）
     */
    private static void testAveragingDouble() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
    /**
     * 求平均值（int）
     */
    private static void testAveragingInt() {
        System.out.println("testAveragingInt");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
    /**
     * 求平均值（Long）
     */
    private static void testAveragingLong() {
        System.out.println("testAveragingLong");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }

    /**
     * 求平均值,并对结果进行操作
     */
    private static void testCollectingAndThen() {
        System.out.println("testCollectingAndThen");
        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "The Average Calories is->" + a)))
                .ifPresent(System.out::println);
/*
        List<Dish> list = menu.stream().filter(d -> d.getType().equals(Dish.Type.MEAT))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        list.add(new Dish("", false, 100, Dish.Type.OTHER));
        System.out.println(list);*/
    }

    /**
     * 求对象的总个数
     */
    private static void testCounting() {
        System.out.println("testCounting");
        Optional.of(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
    }

    /**
     * 对集合数据进行分组(根据对象中的某一个属性)
     */
    private static void testGroupingByFunction() {
        System.out.println("testGroupingByFunction");
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(System.out::println);
    }

    /**
     * 对集合根据对象中某个属性进行分组，并计算每一个组的平均值
     */
    private static void testGroupingByFunctionAndCollector() {
        System.out.println("testGroupingByFunctionAndCollector");
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }

    /**
     * 对集合根据对象中某个属性进行去重分组，并计算每一个组的平均值
     */
    private static void testGroupingByFunctionAndSupplierAndCollector() {
        System.out.println("testGroupingByFunctionAndSupplierAndCollector");
        Map<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingInt(Dish::getCalories)));

        Optional.of(map.getClass()).ifPresent(System.out::println);
        Optional.of(map).ifPresent(System.out::println);
    }

    /**
     * 求集合中根据某个字段的的属性值，求总个数，总值，最小值，平均值，最大值
     */
    private static void testSummarizingInt() {
        System.out.println("testSummarizingInt");
        IntSummaryStatistics result = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        Optional.of(result).ifPresent(System.out::println);
    }
}