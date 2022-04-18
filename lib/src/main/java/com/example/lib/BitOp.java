package com.example.lib;

/**
 * 位运算
 * 多加利用操作符的特性
 * 1.与 & 00为0 11为1 01为0
 * 2.或 | 00为0 11为1 01为1
 * 3.异或 ^ 00为0 11为0 01为1
 */
public class BitOp {

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 输入: [2,2,1]
     * 输出: 1
     *
     * 输入: [4,1,2,1,2]
     * 输出: 4
     *
     * 思路 通过异或的特性 算法具有交换性 相同数异或操作得到为0  再异或剩余数则保持不变
     * 时间复杂度O(n) 空间复杂度O(1)
     *
     */
    public int singleNumber(int[] nums) {
        int value = 0;
        for(int i=0;i<nums.length;i++){
            value = value ^ nums[i];
        }
        return value;
    }
}
