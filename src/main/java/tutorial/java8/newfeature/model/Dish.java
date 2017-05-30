/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature.model;

/**
 * @author hufeng
 * @version $Id: Dish, v0.1 2017年05月30日 3:47 PM hufeng Exp $
 */
public class Dish {
    private String  name;
    private boolean vegetarian;
    private int     calories;
    private Type    type;

    public Dish() {
    }

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * Getter method for property name.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property name.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property vegetarian.
     *
     * @return property value of vegetarian
     */
    public boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * Setter method for property vegetarian.
     *
     * @param vegetarian value to be assigned to property vegetarian
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * Getter method for property calories.
     *
     * @return property value of calories
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Setter method for property calories.
     *
     * @param calories value to be assigned to property calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Getter method for property type.
     *
     * @return property value of type
     */
    public Type getType() {
        return type;
    }

    /**
     * Setter method for property type.
     *
     * @param type value to be assigned to property type
     */
    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        MEAT, FISH, OTHER
    }

}
