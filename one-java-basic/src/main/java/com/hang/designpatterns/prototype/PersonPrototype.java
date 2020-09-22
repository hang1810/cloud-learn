package com.hang.designpatterns.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式  需要实现Cloneable
 * @author Hang
 * @date 2020-09-22 19:46
 */
public class PersonPrototype implements Cloneable{
    private String name;
    private int age;
    private List<String> qianduLists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getQianduLists() {
        return qianduLists;
    }

    public void setQianduLists(List<String> qianduLists) {
        this.qianduLists = qianduLists;
    }

    @Override
    public String toString() {
        return "name="+this.name + "  age="+this.age;
    }

    //浅度克隆
    @Override
    public PersonPrototype clone(){
        try {
            return  (PersonPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return  null;
        }
    }
    //浅度克隆 1). 实现Cloneable接口并重写Object类中的clone()方法；  2). 实现Serializable接口，通过对象的序列化和反序列化实现克隆，可以实现真正的深度克隆。
//    @Override  //实际应该打开
    public PersonPrototype deepClone(){
        try {
            PersonPrototype personPrototype = (PersonPrototype) super.clone();
            List<String> deepCloneLists = new ArrayList<String>();
            for (String deepCloneList : this.getQianduLists()){
                deepCloneLists.add(deepCloneList);
            }
            personPrototype.setQianduLists(deepCloneLists);
            return  personPrototype;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return  null;
        }
    }


}
