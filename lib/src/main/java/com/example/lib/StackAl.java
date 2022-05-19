package com.example.lib;

import java.util.ArrayList;
import java.util.List;

public class StackAl {

    /**
     * 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * 1 2 3 4
     *
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     *
     * 递归将选取的过程思路统一
     *
     * 如果选了第n个数 其 其余的k-1个数从1～n-1范围内选取
     * 如果没选第n个数  其余的k个数从1~n-1范围内选取
     *
     * C(n,k) = C(n-1,k)+C(n-1,k-1)
     *
     * List =  1,2,3,4
     *
     * subString
     *
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        return null;
    }

    public void combine(List<Integer> nums,int n){
        List<Integer> result = new ArrayList<>();
        for (int i = 1;i<=n;i++){
            result.add(i);
        }
    }
}
