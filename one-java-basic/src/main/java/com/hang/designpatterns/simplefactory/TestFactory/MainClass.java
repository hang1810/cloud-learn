package com.hang.designpatterns.simplefactory.TestFactory;

import java.util.Scanner;

/**
 * 工厂模式的简单应用，，按照步骤二 需要再去修改步骤二 代码确实复用 ，可变更为
 * @author Hang
 * @date 2020-09-16 23:36
 */
public class MainClass {
    public static void main(String[] args) {
        // 1 控制台输入
        System.out.println("--计算器程序");
        System.out.println("输入第一个操作数");
        Scanner scanner = new Scanner(System.in);
        String strNum1 = scanner.nextLine();

        System.out.println("输入运算符");
        String oper = scanner.nextLine();

        System.out.println("输入第二个操作数");
        String strNum2 = scanner.nextLine();

        double doubleNum1=Double.parseDouble(strNum1);
        double doubleNum2=Double.parseDouble(strNum2);
        double result = 0;
        //2、运算

        //最原始方法
//        if ("+".equals(oper)){
//        result = doubleNum1+doubleNum2;
//        }else if ("-".equals(oper)){
//            result = doubleNum1-doubleNum2;
//        }

        //创建类来实现， 但是这样还是得知道运算符判断
//        if ("+".equals(oper)){
//            Operation operation = new AddOpertion();
//            operation.setNum1(doubleNum1);
//            operation.setNum2(doubleNum2);
//            result = operation.getResult();
//        }else if ("-".equals(oper)){
//            result = doubleNum1-doubleNum2;
//        }

        //简单工厂方法  oper 进行判断
//        Operation operation = OperationFactory.getOperation(oper);
//        operation.setNum1(doubleNum1);
//        operation.setNum2(doubleNum2);
//        result = operation.getResult();


        //工厂方法
        if ("+".equals(oper)){
            OperationFactory factory = new AddOperationFactory();
            Operation operation =factory.getOperation();
            operation.setNum1(doubleNum1);
            operation.setNum2(doubleNum2);
            result = doubleNum1+doubleNum2;
        }else if ("-".equals(oper)){
            result = doubleNum1-doubleNum2;
        }

        //3、返回结果
        System.out.println(strNum1+oper+strNum2+"="+result);
    }
}
