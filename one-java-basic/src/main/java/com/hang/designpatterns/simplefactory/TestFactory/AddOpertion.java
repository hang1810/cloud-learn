package com.hang.designpatterns.simplefactory.TestFactory;

/**
 * @author Hang
 * @date 2020-09-16 23:46
 */
public class AddOpertion extends Operation {
    @Override
    public double getResult() {
        double result = this.getNum1() + this.getNum2();
        return result;
    }
}
