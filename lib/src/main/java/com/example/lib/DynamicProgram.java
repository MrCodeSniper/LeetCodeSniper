package com.example.lib;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 动态规划
 */
public class DynamicProgram {

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
     *
     *
     *
     * @param n 楼梯阶数
     * @return 方法数
     */

    public static HashMap<Integer,Integer> cache = new HashMap<>();

    {
        cache.put(1,1);
        cache.put(2,2);
    }

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
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
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
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
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
