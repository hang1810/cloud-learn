package com.hang.designpatterns.simplefactory;

/**
 * @author Hang
 * @date 2020-09-15 23:47
 */
public  class FruitSimpleFactory{
    /*
     * get方法获取所有产品实例
     */
    public  static Fruit getFruit(String type)  {
        if (type == null){
            System.out.println("类型为空，无法返回实例化类");
            return null;
        }else if(type.equals("Apple")){
            return  new Apple();
        }else if (type.equals("Banana")){
                return new Banana();//  return SimpleFactoryTest.Banana.class.newInstance();
        }else if (type.equals("Pear")){
            return new Pear();//  return SimpleFactoryTest.Pear.class.newInstance();
        }else{
            System.out.println("找不到相应的实例化类");
            return  null;
        }

    }
    /*
    *  获取实例通过Class.forName()
    */
    public  static Fruit getFruit2(String type) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class fruit = Class.forName(type);
        return ((Fruit) fruit.newInstance());
    }
}