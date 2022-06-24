package com.example.lib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 动态规划
 */
public class DynamicProgram {

    /**
     * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。(2020字节原题)
     *
     * 要求时间复杂度为O(n)。 (暴力不允许)
     *
     * 思路 动态规划解法
     *  1。定义状态方程 需要找到子问题 假设 我们要寻找以f(n)为结尾的的连续子数组最大值
     *  尝试找到f(n-1) 如果以f(n-1)为结尾的连续子数组 序列和<0 那么需要抛弃前面的
     *  如果>0需要加上前面的
     *  2。递推子问题
     *  f(n) = Math.max(array[n],f(n-1)+array[n])
     *  3.边界情况
     *  f(0) = array[0]
     *  4.返回值
     *  循环进行上述操作 计算最大值返回
     *
     *
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int[] temp  =new int[nums.length]; //初始化辅助数组
        int max = nums[0];
        temp[0] = max;
        for(int i=1;i<nums.length;i++){
            temp[i] = Math.max(nums[i],temp[i-1]+nums[i]);
            max = Math.max(max,temp[i]);
        }
        return max;
    }



    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
     *     请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
     *
     * 思路1。暴力解法 循环每一位 从中找出以该位为开头的最长子串长度
     * 时间复杂度为O(n^2)  空间复杂度为O(N)
     *
     * 思路2。双指针法 通过左范围和右范围指针的符合条件的移动来形成子串
     * 但需要保证这两个指针对应的子串中没有重复的字符。
     *      * 在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。
     *      * 我们记录下这个子串的长度
     * 时间复杂度为O(N) 空间复杂度O(n)
     *
     * 思路3. 使用递归和动归规划 找出子问题 总结出公式
     * 将每次寻找的子串长度递或递减
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        return 0;
    }

    public static int lengthOfLongestSubstringDoubleEntry(String s) {
        int maxLength = 0;
        Set<Character> occ = new HashSet<Character>();
        int end = -1;//右边指针
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //每次循环左指针往右挪一格 并从集合中删除该元素
            if (i > 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (end + 1 < chars.length && !occ.contains(s.charAt(end + 1))) { //右指针到达最后一个节点退出循环 如果后续的字符已存在集合内退出循环
                //每次循环 将不重复的子串元素加入集合
                occ.add(s.charAt(end + 1));
                end++;
            }
            //每次循环计算maxLength
            if (maxLength < occ.size()) {
                maxLength = occ.size();
            }
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstringViolent(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        for(int i=0;i<chars.length;i++){
//            System.out.print("当前开始字符:"+chars[i]);
            HashSet<Character> set = new HashSet<>();
            set.add(chars[i]);
            max = Math.max(max,set.size());
            int index = i+1;
            while (index<chars.length){
                char now = chars[index];
               // System.out.print(",当前循环到:"+now);
                if(set.contains(now)){
                    break;
                }
                set.add(now);
                //System.out.print(",当前容器长度:"+set.size());
                max = Math.max(max,set.size());
                index++;
            }
//            System.out.println("");
        }
        return max;
    }

    /**
     * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
     * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     *
     * 输入: 12258  133444
     * 输出: 5
     * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
     * 1位换算 = 1  2为换算 其他为1位为N-1  两两换算 2
     *
     * 思路  一个字母可以翻译的数字可为1位 最多为两位 有点类似跳台阶的题目
     *
     * 子问题 f(0) = 1 f(1) = 1   f(2) = 2   f(3) 180 =  3
     *
     * 边界场景 直接翻译两位数 必须>=10 <=25 否则只能以一位翻译
     *
     *  正常情况下f(n) = f(n-1)+f(n-2)
     *  超出边界情况下 f(n) = f(n-1)
     *
     *  <img width="640" height="480" src="https://pic.leetcode-cn.com/e231fde16304948251633cfc65d04396f117239ea2d13896b1d2678de9067b42-Picture1.png" alt="">
     *
     *
     *  如何理解这个图
     *
     *  如果1235 只翻译5 那么组合就为dp(i-1)
     *  如果1235 将35翻译为整体 那么组合就为dp(i-2)
     *  那么总组合数为dp(i-1)+dp(i-2) 如果35不符合条件那么仅为dp(i-1)
     *
     *
     *
     *
     * @param num
     * @return
     */
    public static int translateNum(int num) {
        cache.clear();
        cache.put(0,1);
        cache.put(1,1);
        cache.put(2,2);
        char[] text = String.valueOf(num).toCharArray();
        for(int i=2;i<=text.length;i++){
            int n = (text[i - 2] - '0') * 10 + (text[i - 1] - '0');//得到数字
            if(n>=10&&n<=25){
                cache.put(i,cache.get(i-1)+cache.get(i-2));
            }else {
                cache.put(i,cache.get(i-1));
            }
        }
        return cache.get(text.length);
    }


    /**
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     *
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 思路 左边小 右边大 的情况才有可能盈利 否则为0
     *
     * [1,6,2,6]
     * 1.循环解题-从左边循环到右边找最小值和最大值  如果找到最小值 那么最大值要重新寻找   每次循环就计算差值的最大值
     * 时间复杂度为O(n) 空间复杂度为O(1)
     *
     * 2.动态规划解题 总结公式使用递归 拆解子问题
     * 将问题划分为 持有股票f1 -值 和 最大盈利f2
     * 初始持有为f1 = -prices[0]
     * 最大盈利为f0 = 0
     * 从1～n循环 每次循环先计算最大盈利并保存f0  随后找到最小持有负载 f1 下次循环会找这个最小持有进行计算得到盈利 返回盈利最大值
     *
     *
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int divide = 0;
        int max = 0; int min = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < min) {
                min = price;
                max = 0;
            } else if (price > min) {
                max = price;
            }
            if (divide < (max - min)) {
                divide = max - min;
            }
        }
        return divide;
    }


    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     *
     * F(0) = 0, F(1)= 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     *
     * 示例 1：
     *
     * 输入：n = 2
     * 输出：1
     * 示例 2：
     *
     * 输入：n = 5
     * 输出：5
     *
     * 第一种思路 当然是使用递推公式+缓存进行计算 需要对结果进行取模 这样的时间复杂度为O(N) 空间复杂度也为O(N)
     *
     * 从优化空间的角度来说 我希望空间能优化到常数集 那么我只需要三个常数来保存需要的数据即可
     * 即从2~N的循环中 我保存三个数 n-2 n-1 n 每次循环滚动这三个数 一直保持这种关系直到n=n即可
     * 这样空间复杂度能优化到O(1)
     *
     * 从优化时间的角度来说 比O(n)更小的时间为O(logn)
     *
     * 矩阵快速幂 TODO
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        int mod = 1000000007;
        if(map.get(n)!=null) return map.get(n);
        int pre = fib(n-1);
        map.put(n-1,pre);
        int after = fib(n-2);
        map.put(n-2,after);
        int result = pre+after;
        result = result%mod;
        map.put(n,result);
        return result;
    }

    public static int fib2(int n){
        int mod = 1000000007;
        if(n<2) return n;
        int p = 0,q = 0,r = 1;  //从2开始循环 每次循环左移 r = 左右两数相加
        for(int i=2;i<=n;i++){
            p = q;
            q = r;
            r = (p+q)%mod;
        }
        return r;
    }

    public static HashMap<Integer,Integer> map = new HashMap<>();

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     *
     * 输入：n = 3
     * 输出：3
     * 解释：有三种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 2 阶
     * 3. 2 阶 + 1 阶
     *
     * 输入: n = 4
     * 解释：有五种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶 + 1 阶 + 1 阶
     * 2. 1 阶 + 1 阶 + 2 阶
     * 3. 1 阶 + 2 阶 + 1 阶
     * 4. 2 阶 + 1 阶 + 1 阶
     * 5. 2 阶 + 2 阶
     *
     * 动态规划 总结数学规律 f(n) = f(n-1) + f(n-2) 但如果N太大 递归层级过深 导致超时
     * 可以使用缓存 去除重复步骤
     * 将每次运行的结果存入缓存
     *
     * 或者使用数组来缓存  第n个位置的值为前两个元素值相加
     *
     * 第二种利用数学特性 特征方程 输出公式 f(n) = ........
     * Todo 待熟练
     *
     *
     *
     * @param n 楼梯阶数
     * @return 方法数
     */

    public static HashMap<Integer,Integer> cache = new HashMap<>();

//    {
//        cache.put(1,1);
//        cache.put(2,2);
//    }

    public int climbStairs(int n) {
        if(cache.containsKey(n)){
            return cache.get(n);
        }
        int result =  climbStairs(n-1) + climbStairs(n-2);
        cache.put(n,result);
        return result;
    }

    /**
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     *
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     *
     * 也就是每一层节点
     *
     * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
     * 输出：11
     * 解释：如下面简图所示：
     *    2               2         2        2
     *   3 4             3 4       3 4      3  4
     *  6 5 7         10 11 1     5 6 7
     * 4 1 8 3                  10 11 1 5
     * 自顶向下的最小路径和为11（即，2+3+5+1= 11）。
     *
     * 输入：triangle = [[-10]]
     * 输出：-10
     *
     * 思考 如果只有1个数组 一层 那么就是取第0位
     * 如果有2层 取较小的那个数
     * 如果有三层 肯定是取最小的数 如果这个数可以被前面最小的引用到那么他就是最小 如果不能 那么要进行比较
     *
     * 或者对所有数组进行排序 那么最小的肯定数左边一条 这样不行 因为每个能索引到的元素有限
     *
     * 总结数学公式
     * f[i][j] =  triangle[i][j] + min(f[i+1][j],f[i+1][j+1])
     * 下标为i行j列的点 到最底边的最小路径和为  = 当前点的值+ (相邻下标的两个点到底部的最小路径和)
     * 自上而下 顶点值 与下面两个有关 同理递推 直到底端其最小值就是其点本身
     * 递归会导致超时 需要计算中间步骤 缓存
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        memo = new Integer[triangle.size()][triangle.size()];
        return digui(triangle,0,0);
    }

    public static  Integer[][] memo;

    /**
     * 递归处理 求该点到底边的最小路径和
     * @param triangle 三角形
     * @param i 层
     * @param j 位置
     * @return
     */
    public int digui(List<List<Integer>> triangle,int i,int j){
        if(triangle.size() == i){ //相同层级没有底边 返回0
            return 0;
        }
        if(memo[i][j]!=null){
            return memo[i][j];
        }
        //根据公式编写代码
        //将每次递归的结果与参数对应 方便下次使用
        return memo[i][j] = triangle.get(i).get(j)+Math.min(digui(triangle,i+1,j),digui(triangle,i+1,j+1));
    }


    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * 思考
     * [1]
     * [1,2]
     * [1,2,3]
     * [1,2,3,4,5]
     * [1,2,3,4,5,6]
     * [1,2,3,4,5,6,7] 如果我选择了1有2种方案  1,3,5,7  1,4,6 选偶数 选奇数 如果我选了2   4，6  5，7 也有两种方案
     *
     * 但是也可以奇偶混合 所以不一定只有两种方案
     *
     * 6,3,10,8,2,10,3,5,10,5,3 为39
     *
     * 6 [10,8,2,10,3,5,10,5,3]
     *
     * 10 [2,10,3,5,10,5,3]
     *
     * 10 [5,10,5,3]
     *
     * 10 [3]
     *
     * 3
     *
     * 1，3 ,6,9,11
     *
     * 动态规划的的四个解题步骤是：
     *
     * 定义子问题
     * 写出子问题的递推关系
     * 确定 DP 数组的计算顺序
     * 空间优化（可选）
     *
     * f(k)=max{f(k−1) ,Hk−1 + f(k−2)}
     *
     * dp[i]=max(dp[i−2]+nums[i],dp[i−1])
     *
     * 只有一间房屋，则偷窃该房屋
     * 只有两间房屋，选择其中金额较高的房屋进行偷窃
     *
     *
     *
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums,int start,int end) { //1,1,1,1
        Integer cacheResult = maxTotalCache.get(new Node(start,end));
        if(cacheResult!=null){
            return cacheResult;
        }
        if(start == end){
            return nums[end];
        }
       int total1;
       int total2;
       if(start+1<=end){ //1,2,3,1
           if(start+2<=end){
               int robResult = rob(nums,start+2,end); //如果选中某个数 只能取相邻+1的地方作为区间求和 这个区间求的值不一定是最大的
               maxTotalCache.put(new Node(start+2,end),robResult);
               total1 = nums[start] + robResult;
           }else{
               total1 = nums[start];
           }
           if(start+3<=end){
               int robResult = rob(nums,start+3,end); //如果选中某个数 只能取相邻+2的地方作为区间求和 这个区间求的值不一定是最大的
               maxTotalCache.put(new Node(start+3,end),robResult);
               total2 = nums[start+1] + robResult;
           }else{
               total2 = nums[start+1];
           }
       }else {
           return nums[end];
       }
       int result = Math.max(total1,total2); //将两个区间得到值比较返回
       maxTotalCache.put(new Node(start,end),result);
       return result;
    }

    class Node{
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return start == node.start && end == node.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    public static HashMap<Node,Integer> maxTotalCache = new HashMap<>(); //缓存数组下标对应的结果

    public int rob(int[] nums){
        if(nums.length>0){
            maxTotalCache.put(new Node(0,0),nums[0]);
        }
        if(nums.length>1){
            maxTotalCache.put(new Node(0,1),Math.max(nums[0],nums[1]));
        }
       int result = rob(nums,0,nums.length-1);
       maxTotalCache.clear();
       return result;
    }
}
