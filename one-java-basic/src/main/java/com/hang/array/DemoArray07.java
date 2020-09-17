package com.hang.array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Hang
 * @date 2020-08-28 16:02
 */
public class DemoArray07 {
    public static void main(String[] args) {
        int[] arr = new int[]{3,2,14,6,1};
        Arrays.sort(arr);//升序

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        //插入 替换
//        Arrays.fill(arr,5);
//        for(int i=0;i<arr.length;i++){
//            System.out.print(arr[i]+" ");
//        }

        Arrays.sort(arr);
        //通过二分法，在arr中查找2的下标（前提是 数组已经排好序）
        int position = Arrays.binarySearch(arr, 14);
        System.out.println(position);
        int position1 = Arrays.binarySearch(arr, 2);
        System.out.println(position1);
        int position2 = Arrays.binarySearch(arr, 3);
        System.out.println(position2);


    }

//    class MyComparatorWithId implements Comparator {
//        @Override
//        public int compare(Object o1, Object o2) {
//            Student s1 =  (Student)o1 ;
//            Student s2 =  (Student)o2 ;
//            return   s2.getId() -  s1.getId()  ;
//        }
}
