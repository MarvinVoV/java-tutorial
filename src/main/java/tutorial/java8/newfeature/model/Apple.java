/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature.model;

/**
 * @author hufeng
 * @version $Id: Apple, v0.1 2017年06月30日 8:13 PM hufeng Exp $
 */
public class Apple {
    private int weight;

    public Apple() {}

    public Apple(int weight) {
        this.weight = weight;
    }



    /**
     * Getter method for property weight.
     *
     * @return property value of weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Setter method for property weight.
     *
     * @param weight value to be assigned to property weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
