package com.example.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortAl {

    /**
     * 有序数组的平方
     * 双指针 数组的前后各一个指针 将绝对值小的插入后 i++或j-- 直到i>j i=j为相遇
     *
     * @param nums 旧数组
     * @return 新数组
     */
    public static int[] sortedSquares(int[] nums) {
        int[] results = new int[nums.length];

        //从0和最大值出发 取大的数加入到数组最后一位 如果是正则-- 如果是负则++ 直到相遇
        //这样的好处是下标一定在范围内无需判断越界
        for (int i = 0, j = nums.length - 1, index = nums.length - 1; i <= j; ) { //将i++ 将由函数体内执行
            int start = nums[i];
            int end = nums[j];
            if (Math.abs(start) >= Math.abs(end)) {
                results[index--] = start * start;
                i++;
            } else {
                results[index--] = end * end;
                j--;
            }
        }
        System.out.println(Arrays.toString(results));
        return results;
    }

    /**
     * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个
     *
     * 输入: [10,2]   10 2
     * 输出: "102"
     *
     * 输入: [3,30,34,5,9]  [1,839,2,6,8]       30 3 34 5 9   30 3 34 5 9
     * 输出: "3033459"
     *
     * [1,99,0,2,88] 数字最小是 最高位要小 取最高位最小的数  如果最高位相同 怎么办 取这几个中最小的 0 1 2 88 99
     *
     * [1,32,9,8,76]  1 32 76 8 9
     *
     *
     * 思路
     * 1.如果整数都小于10  拆分 [1,0,2] [3,3,0,3,4,5,9] 那么最小的数即从小到大排序
     * 012 但是因为10需要保持10的顺序 所以不成立  0 3 3 3 4 ｜ 5 9
     *
     * 解答:
     * 将整体看成排序 并将排序判断的条件为 两个数字符串拼接小的数 考虑到边界问题 用字符串comparto判断
     * 具体效率取决于使用的具体算法
     *
     * 3 30    303 < 330 所以 30要排到前面 每个都互相比较过
     *
     *
     * @param nums
     * @return
     */
    public static String minNumber(int[] nums) {
        String[] strings = new String[nums.length];
        //1.转换为List
        for(int i = 0;i<nums.length;i++){
            int element = nums[i];
            strings[i] = element+"";
        }
        bubbleSort(strings);
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<strings.length;i++){
            result.append(strings[i]);
        }
        return result.toString();
    }


    //按照刚才那个动图进行对应
//冒泡排序两两比较的元素是没有被排序过的元素--->
    public static void bubbleSort(String[] array){
        for(int i=0;i<array.length-1;i++){//控制比较轮次，一共 n-1 趟
            for(int j=0;j<array.length-1-i;j++){//控制两个挨着的元素进行比较
                //3和30
                String x = array[j] + array[j + 1]; //330
                String y = array[j+1] + array[j]; //303
                if(y.compareTo(x)<0){ //需要交换 把30往前
                    String temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }


}
