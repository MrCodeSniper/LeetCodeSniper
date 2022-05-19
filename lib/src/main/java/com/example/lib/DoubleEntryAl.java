package com.example.lib;

public class DoubleEntryAl {

    /**
     * i++ j--
     * 双指针 头尾如果头遇到了0 那么下标后面的元素都要左移一位 最后一个为0 用j指向0之前的下标 直到遍历完成
     * 直到 下标相遇
     * <p>
     * 左指针指向当前已经处理好的序列的尾部 即交换完的尾部
     * 右指针指向待处理序列的头部
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (nums[i] == 0) {
                for (int k = i; k < j; k++) {
                    nums[k] = nums[k + 1];
                }
                nums[j] = 0;
                j--;
            } else {
                i++;
            }
        }
    }
}
