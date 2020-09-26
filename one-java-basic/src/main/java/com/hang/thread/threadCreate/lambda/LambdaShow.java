package com.hang.thread.threadCreate.lambda;

/**
 * @author Hang
 * @date 2020-09-26 14:56
 */
public class LambdaShow {
    //3、静态内部类
    static class Love2 implements ILove{
        @Override
        public void love(String name) {
            System.out.println("I Love "+name);
        }
    }

    public static void main(String[] args) {
        ILove love = new Love();
        love.love("1-实现类Love");

        ILove love2 = new Love2();
        love2.love("2-实现类静态内部类");

        //4、局部内部类
        class Love3 implements ILove{
            @Override
            public void love(String name) {
                System.out.println("I Love "+name);
            }
        }

        ILove love3 = new Love3();
        love3.love("3-实现类局部内部类");

        //5、匿名内部类 没有类的名称，必须借助接口或者父类
        ILove love4 = new ILove() {
            @Override
            public void love(String name) {
                System.out.println("I Love "+name);
            }
        };
        love4.love("4-实现类匿名内部类");

        //6、用lambda
        ILove love5 =(String name) ->{ System.out.println("I Love "+name);  };
        love5.love("5-实现类lambda");

    }
}

//1、函数式接口，只有一个方法
interface ILove{
    void love(String name);
}
//2、实现类
class Love implements ILove{

    @Override
    public void love(String name) {
        System.out.println("I Love "+name);
    }
}