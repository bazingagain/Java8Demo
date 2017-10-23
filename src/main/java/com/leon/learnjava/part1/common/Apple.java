package com.leon.learnjava.part1.common;

/**
 * Created on 22/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class Apple implements Fruit{
    private Integer weight;
    private String color;

    public Apple() {

    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple(Integer weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
