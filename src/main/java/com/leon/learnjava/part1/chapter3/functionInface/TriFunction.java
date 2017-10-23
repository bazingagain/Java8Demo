package com.leon.learnjava.part1.chapter3.functionInface;

/**
 * Created on 23/10/2017.
 * (T, U, V, R) -> R
 * @author Xiaolei-Peng
 *
 */
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R applay(T t, U u, V v);
}
