package com.example.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 查找算法
 * 冒泡
 * 二分
 * 快排
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


    /**
     * 找出数组中重复的数字。
     *
     *
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     *
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     *
     * 思路
     * 1.利用Map特性 Key为值 出现次数+1 时间复杂度 O(n) 空间复杂度 O(n)
     * 2.利用双循环两两比较 判断重复 时间复杂度 O(n^2) 空间复杂度 O(1)
     * 3.通过HashSet数据结构 添加进去得到返回值
     * 4.sort排序过后 相邻比较
     *
     * 5。遍历每个位置： 值跟下标结合
     * 1. 如果发现当前元素num已经躺在正确的位置上，continue;
     * 2. 否则，即num不在正确位置上：
     *   1）如果发现其正确的位置上已经躺着num了，即发现重复元素，return num;
     *   2）否则，开启循环移位流程，将num发往正确位置上，其怼出来的元素，继续发往它应该去的地方，如此循环。。。
     *
     *   时间复杂度 O(n) 空间复杂度 O(1)
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
//        HashMap<Integer,Integer> map = new HashMap<>();
//        for(int i = 0;i<nums.length;i++){
//            int num = nums[i];
//            if(map.get(num)!=null){
//                map.put(num,map.get(num)+1);
//            }else {
//                map.put(num,1);
//            }
//        }
//        final int[] result = {-1};
//        map.entrySet().forEach((Consumer<Map.Entry<Integer, Integer>>) integerIntegerEntry -> {
//            if(integerIntegerEntry.getValue()>1){
//                result[0] =  integerIntegerEntry.getKey();
//            }
//        });
//        return result[0];

        for(int i = 0;i<nums.length;i++){ //因为内部循环会放到正确位置 两种循环加起来为o(n)时间 空间复杂度为O(1)
            int num = nums[i];
            if(num == i) continue; //当前元素num已经躺在正确的位置上，continue
            if(nums[num] == num) return num; //如果发现其正确的位置上已经躺着num了，即发现重复元素，return num;
            int target = num;
            while (nums[target]!=target){ //如果该下标下的值 与下标不同进行移位 并循环
                int exist = nums[target]; //该下标原值
                nums[target] = num; //将该下标的值改为
                num = exist;
                target = num;
            }
        }
        return 0;
    }

    /**
     * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     *
     * 思路
     * 1.公式 (n+1)n/2
     * 2.递归 3+2+1+0 return
     *
     * 输入: n = 3
     * 输出: 6 = 1+2+3
     *
     * 输入: n = 9
     * 输出: 45
     *
     * @param n
     * @return
     */
    public static int sumNums(int n) {
        //n递归-1会出现负数 需要停到0 但是不能用三元表达式 if else 我们利用位运算特性 && 如果n<=0 就执行return 不走后面的+=
        boolean flag = n>0 && (n+=sumNums(n-1))>0;
        return  n;
    }
}
