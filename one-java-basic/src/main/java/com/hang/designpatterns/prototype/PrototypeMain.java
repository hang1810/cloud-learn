package com.hang.designpatterns.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式
 * @author Hang
 * @date 2020-09-22 19:47
 */
public class PrototypeMain {
    public static void main(String[] args) {
        //原型模式使用场景如下，需实现两对象，两对象具有大部分数值相同，部分不同
        PersonPrototype personPrototype = new PersonPrototype();
        personPrototype.setName("hang1");
        personPrototype.setAge(25);
        System.out.println(personPrototype.toString());

        PersonPrototype personPrototype2 = new PersonPrototype();
        personPrototype2.setName("hang1");
        personPrototype2.setAge(24);
        System.out.println(personPrototype2.toString());


        PersonPrototype personPrototype3 =personPrototype;// 这样personPrototype3 和 personPrototype 是同一个对象
        PersonPrototype personPrototypePrototype =personPrototype.clone();// 这样克隆的personPrototypePrototype 和 personPrototype 是两个不同的对象，但内部值是一样的

        System.out.println("改变前personPrototype.getName()="+personPrototype.getName());
        System.out.println("改变前personPrototype3.getName()="+personPrototype3.getName());
        System.out.println("改变前personPrototypePrototype.getName()="+personPrototypePrototype.getName());
        //此时改变 personPrototype Name值 再查看 personPrototypePrototype的值
        personPrototype.setName("改变的值");
        System.out.println("改变后personPrototype.getName()="+personPrototype.getName());
        System.out.println("改变后personPrototype3.getName()="+personPrototype3.getName());
        System.out.println("改变后personPrototypePrototype.getName()="+personPrototypePrototype.getName());
        //可以发现克隆来的personPrototypePrototype 并不会发生该变，而这样personPrototype3的值会改变


        //针对属性内含有对象
        //浅度克隆
        System.out.println("浅度克隆");
        PersonPrototype qianduPerson = new PersonPrototype();
        List<String> qianduLists = new ArrayList<String>();
        qianduLists.add("James");
        qianduLists.add("Tom");

        qianduPerson.setQianduLists(qianduLists);

        PersonPrototype personPrototypeForList = qianduPerson.clone();//此处采用浅度克隆
        System.out.println("改变前qianduPerson.getQianduLists()"+qianduPerson.getQianduLists());
        System.out.println("改变前personPrototypeForList.getQianduLists()"+personPrototypeForList.getQianduLists());

        //改变其中一个，看另一个是否跟着变化
        qianduLists.add("Jarry");
        qianduPerson.setQianduLists(qianduLists);
        System.out.println("改变后qianduPerson.getQianduLists()"+qianduPerson.getQianduLists());
        System.out.println("改变后personPrototypeForList.getQianduLists()"+personPrototypeForList.getQianduLists());
        //可以得到 qianduPerson克隆的personPrototypeForList  两者指向的  List是同一个 ，指向同一个地址，并非各自拥有的对象，当然name age 这些是各自
        //通过变更需克隆类 PersonPrototype 克隆方法

        //深度克隆
        System.out.println("深度克隆");
        PersonPrototype deepClonePerson = new PersonPrototype();
        List<String> deepCloneLists = new ArrayList<String>();
        deepCloneLists.add("James");
        deepCloneLists.add("Tom");

        deepClonePerson.setQianduLists(deepCloneLists);

        PersonPrototype deepClonePersonForList = deepClonePerson.deepClone();//此处使用深度克隆
        System.out.println("改变前deepClonePerson.getQianduLists()"+deepClonePerson.getQianduLists());
        System.out.println("改变前deepClonePersonForList.getQianduLists()"+deepClonePersonForList.getQianduLists());

        //改变其中一个，看另一个是否跟着变化
        deepCloneLists.add("Jarry");
        deepClonePerson.setQianduLists(deepCloneLists);
        System.out.println("改变前deepClonePerson.getQianduLists()"+deepClonePerson.getQianduLists());
        System.out.println("改变前deepClonePersonForList.getQianduLists()"+deepClonePersonForList.getQianduLists());
        //可以得到 qianduPerson克隆的personPrototypeForList  两者deepCloneLists内值不一样指向的  List不是同一个，各自拥有的对象，与各自拥有name age 一致


        //深度克隆另一形式
        System.out.println("序列化深度克隆");
        DeepClonePersonPrototy deepClonePersonPrototy = new DeepClonePersonPrototy();
        List<String> deepCloneForSerializableLists = new ArrayList<String>();
        deepCloneForSerializableLists.add("James");
        deepCloneForSerializableLists.add("Tom");
        deepClonePersonPrototy.setDeepCloneList(deepCloneForSerializableLists);

        DeepClonePersonPrototy deepClonePersonPrototy1 = deepClonePersonPrototy.myclone();
        System.out.println("改变前deepClonePersonPrototy.getDeepCloneList()"+deepClonePersonPrototy.getDeepCloneList());
        System.out.println("改变前deepClonePersonPrototy1.getDeepCloneList()"+deepClonePersonPrototy1.getDeepCloneList());
        //改变deepClonePersonPrototy 看 deepClonePersonPrototy1是否跟着变化
        deepCloneForSerializableLists.add("Jarry");
        deepClonePersonPrototy.setDeepCloneList(deepCloneForSerializableLists);
        System.out.println("改变前deepClonePersonPrototy.getDeepCloneList()"+deepClonePersonPrototy.getDeepCloneList());
        System.out.println("改变前deepClonePersonPrototy1.getDeepCloneList()"+deepClonePersonPrototy1.getDeepCloneList());
        //可以得到 deepClonePersonPrototy克隆的deepClonePersonPrototy1  两者getDeepCloneLists内值不一样指向的  List不是同一个，各自拥有的对象，与各自拥有name   一致


    }
}
