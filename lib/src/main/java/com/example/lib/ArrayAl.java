package com.example.lib;

public class ArrayAl {

    /**
     * 翻转数组
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     * 1.考察的题目类型 双指针
     * <p>
     * 输入：s = ["h","e","l","l","o"]
     * 输出：    ["o","l","l","e","h"]
     *
     * @param s
     */
    public static void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start <= end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    public static void reverseString(char[] s, int start, int end) {
        while (start <= end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 轮转数组
     * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 解法1：遍历数组 将i+k处于数组范围内的赋值新数组   i+k超出的取余 (i+k)%nums.length;赋值 时空都是O(n)
     * 优化将数组改为常量存储 空间可以降到O(1)
     * 解法2：将每一次移位看成独立的函数 循环移动
     * 解法3：将K区间的数组看成整体区分两个进行排序
     * 解法4：数组翻转 将整体翻转倒序 然后翻转区间 实现区域内正序 和移动
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
//        for (int i = 0;i<k;i++){
//            singleRotate(nums);
//        }
        if (nums.length <= 1) {
            return;
        }
        int[] results = new int[nums.length];
//        k = nums.length-k; //右移3 等于左移4 即i+4位置的值 如果i+4>最大下标 取余
        for (int i = 0; i < nums.length; i++) {
            if ((i + k) <= nums.length - 1) { //如果右移没有超出范围
                results[i + k] = nums[i];
            } else { //如果右移超出范围
                int j = (i + k) % nums.length;
                results[j] = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = results[i];
        }
    }

    public static void singleRotate(int[] nums) {
        int[] results = new int[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            results[i + 1] = nums[i];
        }
        results[0] = nums[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = results[i];
        }
    }
}
