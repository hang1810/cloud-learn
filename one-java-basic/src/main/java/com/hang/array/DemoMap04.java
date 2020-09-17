package com.hang.array;

import java.util.*;

/**
 * @author Hang
 * @date 2020-08-28 14:58
 */
//循环遍历map的方法
public class DemoMap04 {
    public static void main(String[] args) {
        DemoMap04 demoMap04 = new DemoMap04();
//        demoMap04.mapF();
//        demoMap04.addMap();
//        demoMap04.getMap();
    demoMap04.getMap2();
    }

    private void addMap() {
        // 定义一个Map的容器对象
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("jack", 20);
        map1.put("rose", 18);
        map1.put("lucy", 17);
        map1.put("java", 25);
        // map1.put("jack", 30)； 在没有hashCode和equals方式   添加重复的键值（值不同）,会覆盖掉前面key值相同的值
        System.out.println(map1);

        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("张三丰", 100);
        map2.put("虚竹", 20);
        System.out.println("map2:" + map2);
        // 从指定映射中将所有映射关系复制到此映射中。
        map1.putAll(map2);
        System.out.println("map1:" + map1);
    }

    private void removeMap() {
        // 定义一个Map的容器对象
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("jack", 20);
        map1.put("rose", 18);
        map1.put("lucy", 17);
        map1.put("java", 25);
        // map1.put("jack", 30)； 在没有hashCode和equals方式   添加重复的键值（值不同）,会覆盖掉前面key值相同的值
        System.out.println(map1);

        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("张三丰", 100);
        map2.put("虚竹", 20);
        System.out.println("map2:" + map2);
        // 从指定映射中将所有映射关系复制到此映射中。
        map1.putAll(map2);
        System.out.println("map1:" + map1);
    }
    private void getMap() {
        //遍历Map 第一种方式
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("jack", 20);
        map1.put("rose", 18);
        map1.put("lucy", 17);
        map1.put("java", 25);
        System.out.println("============= 第一种通过 map1.keySet() 获取key  通过key 找到value");
        //第一种通过 map1.keySet() 获取key  通过key 找到value
        for (String key : map1.keySet()) {
            Integer value = map1.get(key);
            System.out.println("key : " + key + " value : " + value);
        }
        System.out.println("============= 第二种通过Map.Entry(String,Integer) 获取，然后使用entry.getKey()获取到键，通过entry.getValue()获取到值");
        //第二种通过Map.Entry(String,Integer) 获取，然后使用entry.getKey()获取到键，通过entry.getValue()获取到值
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            System.out.println("键 key ：" + entry.getKey() + " 值value ：" + entry.getValue());
        }
        System.out.println("============= 第三种只遍历键或者值，通过加强for循环");
        //第三种只遍历键或者值，通过加强for循环
        for (String s1 : map1.keySet()) {//遍历map的键
            System.out.println("键key ：" + s1);
        }
        for (Integer s2 : map1.values()) {//遍历map的值
            System.out.println("值value ：" + s2);
        }
        System.out.println("============= 第四种Iterator遍历获取，然后获取到Map.Entry<String, String>，再得到getKey()和getValue()");
        //第四种Iterator遍历获取，然后获取到Map.Entry<String, String>，再得到getKey()和getValue()
        Iterator<Map.Entry<String, Integer>> it = map1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            System.out.println("键key ：" + entry.getKey() + " value ：" + entry.getValue());
        }
    }

    public void getMap2(){
        HashMap<Person, String> hm = new HashMap<Person, String>();
        hm.put(new Person("jack", 20), "1001");
        hm.put(new Person("rose", 18), "1002");
        hm.put(new Person("lucy", 19), "1003");
        hm.put(new Person("hmm", 17), "1004");
        hm.put(new Person("ll", 25), "1005");
        System.out.println(hm);
        System.out.println(hm.put(new Person("rose", 18), "1006"));  //重写hashCode和equalse后key相同不会覆盖

//        Set<Map.Entry<Person, String>> entrySet = hm.entrySet();
//        Iterator<Map.Entry<Person, String>> it = entrySet.iterator();
//        while (it.hasNext()) {
//            Map.Entry<Person, String> next = it.next();
//            Person key = next.getKey();
//            String value = next.getValue();
//            System.out.println(key + "(age:"+key.getAge()+",name:"+key.getName()+") = " + value);
//
//        }
        Set<Map.Entry<Person, String>> entrySet = hm.entrySet();
        Iterator<Map.Entry<Person, String>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<Person, String> next = it.next();
            Person key = next.getKey();
            String value = next.getValue();
            System.out.println(key + "(age:"+key.getAge()+",name:"+key.getName()+") = " + value);

        }

    }

    class Person {
        private String name;
        private int age;

        Person() {

        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

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

        @Override
        public int hashCode() {

            return this.name.hashCode() + age * 37;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Person) {
                Person p = (Person) obj;
                return this.name.equals(p.name) && this.age == p.age;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {

            return "Person@name:" + this.name + " age:" + this.age;
        }

    }
}
