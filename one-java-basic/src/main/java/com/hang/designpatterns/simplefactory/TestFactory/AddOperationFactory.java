package com.hang.designpatterns.simplefactory.TestFactory;

/**
 * @author Hang
 * @date 2020-09-16 23:56
 */
public class AddOperationFactory implements OperationFactory {
    public Operation getOperation(){
        return new AddOpertion();
    }
}
