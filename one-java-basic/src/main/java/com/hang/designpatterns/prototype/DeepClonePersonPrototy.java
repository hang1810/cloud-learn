package com.hang.designpatterns.prototype;

import java.io.*;
import java.util.List;

/**
 * @author Hang
 * @date 2020-09-22 23:04
 */
public class DeepClonePersonPrototy implements Serializable {


    private static final long serialVersionUID = 369285298572941L;  //最好是显式声明ID

    private String name;
    private List<String> deepCloneList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDeepCloneList() {
        return deepCloneList;
    }

    public void setDeepCloneList(List<String> deepCloneList) {
        this.deepCloneList = deepCloneList;
    }

    //Discription:[深度复制方法,需要对象及对象所有的对象属性都实现序列化]　
    public DeepClonePersonPrototy myclone() {
        DeepClonePersonPrototy deepClonePersonPrototy = null;
        try {
            // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            // 将流序列化成对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            deepClonePersonPrototy = (DeepClonePersonPrototy) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deepClonePersonPrototy;
    }
}
