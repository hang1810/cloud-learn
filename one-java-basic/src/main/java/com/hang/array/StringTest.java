package com.hang.array;

import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Hang
 * @date 2020-09-08 8:18
 */
public class StringTest {
    public static void main(String[] args) {
       String str = "上海,上海市,闵行区,吴中路";
//        String[] str1 = str.split(",");
//        for(String str2: str1){
//            System.out.println(str2);
//        }
        System.out.print (str+"       ");
        System.out.println(str);
        Set set = new HashSet();
        JSONObject job = new JSONObject();
            job.put( "id", "1" );
            job.put(  "ouName","第一");
            job.put( "parentId","one");
            set.add(job);
            job =    new JSONObject();
            job.put( "id", "2" );
            job.put(  "ouName","第二");
            job.put( "parentId","two");
        set.add(job);
//        job =    new JSONObject();
        job.put( "id", "1" );
        job.put(  "ouName","第一");
        job.put( "parentId","one");
        set.add(job);
       job =    new JSONObject();
        job.put( "id", "3" );
        job.put(  "ouName","第三");
        job.put( "parentId","three");
        set.add(job);
        System.out.println(set.toString());
    }
}
