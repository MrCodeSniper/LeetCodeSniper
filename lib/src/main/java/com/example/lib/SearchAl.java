package com.example.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 查找算法
 * @author  ch
 */
public class SearchAl {
    /**
     * 二分查找递归方式
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int start, int end, int target) {
        int middle = (start + end) / 2;
        if (start > end) {
            return -1;
        }
        if (nums[middle] == target) {
            return middle;
        } else if (nums[middle] > target) {
            return search(nums, start, middle - 1, target);
        } else if (nums[middle] < target) {
            return search(nums, middle + 1, end, target);
        }
        return -1;
    }



    /**
     * 二分查找While循环
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int middle = (end + start) / 2;
        while (middle >= 0 && start <= end) {
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                end = middle - 1;
            } else if (nums[middle] < target) {
                start = middle + 1;
            }
            middle = (end + start) / 2;
        }
        return -1;
    }

    /**
     * 找到0元素的下标
     *
     * @param nums
     * @return
     */
    public static List<Integer> getZeroSubList(int[] nums) {
        List<Integer> zeros = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros.add(nums[i]);
            }
        }
        return zeros;
    }


    /**
     * 给你一个下标从 1 开始的整数数组numbers ，该数组已按 非递减顺序排列 ，请你从数组中找出满足相加之和等于目标数target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * <p>
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * <p>
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * <p>
     * 你所设计的解决方案必须只使用常量级的额外空间。
     * <p>
     * <p>
     * <p>
     * <p>
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     * <p>
     * 解法1：利用KV容器的特性 对应值和下标 时间空间都是O(n)
     * 解法2: 固定一个数 在剩余数组中利用升序的特性采用二分查找找到另外一个数  时间 O(nlogn) 空间O(1)
     * 解法3: 双指针固定首尾 如果差值在比尾部大 首部进一 否则尾部退一 若找不到 首部进一 尾部还原 时间O(n) 空间O(1)
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] indexs = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }
        for (int i = 0; i < numbers.length; i++) {
            int divide = target - numbers[i];
            if (map.get(divide) != null) {
                if (i < map.get(divide)) {
                    indexs[0] = i + 1;
                    indexs[1] = map.get(divide) + 1;
                } else {
                    indexs[1] = i + 1;
                    indexs[0] = map.get(divide) + 1;
                }
                return indexs;
            }
        }
        return indexs;
    }
}
