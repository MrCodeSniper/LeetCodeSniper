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
     * 去重性 可以想到 Map K为数字 V为数字出现的个数 取V为1的K值
     *
     * 也可以使用集合 每一次插入时判断是否已经存在 则把这个数从原数组删除 最后剩下就是不重复的数
     *
     */
    public int singleNumber(int[] nums) {
        int value = 0;
        for(int i=0;i<nums.length;i++){
            value = value ^ nums[i];
        }
        return value;
    }

    /**
     * 颠倒给定的 32 位无符号整数的二进制位。
     *
     * 输入：n = 00000010100101000001111010011100
     * 输出：964176192 (00111001011110000010100101000000)
     *
     * 思路1. 将数字转换为二进制字符数组 循环数组放入到栈中再转换输出 时间复杂度O(n) 空间复杂度O(n)
     * 思路2. 位运算  TODO 不懂
     *
     */
    public int reverseBits(int n) {
        System.out.println("reverseBits start!");
        char[] character = Integer.toBinaryString(n).toCharArray();
        System.out.print(character);
        return -1;
    }
}
