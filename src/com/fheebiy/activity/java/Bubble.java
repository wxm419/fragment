package com.fheebiy.activity.java;

import com.fheebiy.util.CommonUtil;
import com.fheebiy.util.Log;
import org.junit.Test;

/**
 * Created by Lenovo on 15-3-6.
 */
public class Bubble {


    private static int[] array1 = {1, 4, 5, 2, 1, 3, 7, 11, 9, 6, 8, 15, 14};

    private static int num;


    //冒泡排序
    public static int[] bubble_sort(int[] unsorted) {       //91次循环(13+12+...+3+2+1)=(1+13)*13 = 91;
        for (int i = 0; i < unsorted.length; i++) {         //每一趟找出一个最小的
            for (int j = i; j < unsorted.length; j++) {
                if (unsorted[i] > unsorted[j]) {
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
                num++;
            }
        }

        return unsorted;
    }


    public static void main(String[] a) {
        int[] array = bubble_sort(array1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
        System.out.println();
        System.out.println("fx(7)=" + fx(7));
        System.out.println("循环次数:" + num);
        System.out.println("最大数:" + getMax(array1) );
    }

    //递归算法
    public static int fx(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return fx(n - 1) + fx(n - 2);


    }


    public int[] myBubble(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }


    public static int getMax(int[] array){
        int max = array[0];
        for(int i = 0; i<array.length; i++){
            if(max < array[i]){
                max = array[i];
            }
        }
        return max;

    }



}
