package com.leon.learnjava.chapter1.predicate;

import com.leon.learnjava.chapter1.common.Apple;

/**
 * Created on 22/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
