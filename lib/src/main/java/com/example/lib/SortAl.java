package com.example.lib;

import java.util.Arrays;

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
}
