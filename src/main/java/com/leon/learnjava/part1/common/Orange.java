package com.leon.learnjava.part1.common;

/**
 * Created on 23/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class Orange implements Fruit{
    private Integer weight;

    public Orange(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
