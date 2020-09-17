package com.hang.designpatterns.simplefactory.TestFactory;

/**
 * @author Hang
 * @date 2020-09-16 23:50
 */
public interface OperationFactory {
    /*public static Operation getOperation(String oper){
        if ("+".equals(oper)){
            return new AddOpertion();
        }else {
            return null;
        }
    }*/
    public  Operation getOperation();

}
