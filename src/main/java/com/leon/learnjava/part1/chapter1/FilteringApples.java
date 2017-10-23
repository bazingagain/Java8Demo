package com.leon.learnjava.part1.chapter1;

import com.leon.learnjava.part1.common.Apple;
import com.leon.learnjava.part1.chapter1.predicate.AppleGreenColorPredicate;
import com.leon.learnjava.part1.chapter1.predicate.AppleHeavyWeightPredicate;
import com.leon.learnjava.part1.chapter1.predicate.ApplePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 22/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());

        List<Apple> newHeavyApples = filterApples(inventory, (Apple a) -> a.getWeight() > 100);
        for (Apple a : newHeavyApples) {
            System.out.println(a.getColor() + " " + a.getWeight());
        }
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}
