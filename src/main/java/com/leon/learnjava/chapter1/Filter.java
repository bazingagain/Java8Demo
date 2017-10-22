package com.leon.learnjava.chapter1;

import com.leon.learnjava.chapter1.common.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created on 22/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class Filter {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(120, "red"));
        //测试Comparator
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        //升序
        System.out.println(inventory.get(1).getWeight());
        List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evenNumbers = filter(intList, (Integer i) -> i % 2 == 0);

        for (Integer i : evenNumbers) {
            System.out.println(i);
        }

        //测试Runnable
        Thread t = new Thread(() -> System.out.println("test runnable with lambda"));
        t.start();
    }

    /**
     * 通用方式filter
     * @param list
     * @param p 函数接口(lambda表达式)
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        //1.串行方式
        List<T> result = new ArrayList<T>();
        for (T a : list) {
            if (p.test(a)) {
                result.add(a);
            }
        }
        //2.Stream流方式
        //List<T> result = list.stream().filter(p::test).collect(Collectors.toList());
        return result;
    }
}
