package com.example.lib;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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


    /**
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead操作返回 -1 )
     *
     * 思路
     * 栈结构的出栈和入栈是对顺序的反转 两次反转即可变为正序 符合队列的结构
     * 1.队列尾部插入整数 为入栈Stack1
     * 2.队列头部删除整数 Stack1全部出栈 入栈Stack2 再Stack2出栈一个元素 再入栈Stack1
     *
     * 时间复杂度为O(n) 空间复杂度为O(n)
     *
     *
     * 解答
     * 思路是没错的 但是有许多冗余操作
     * 1.首先Stack1,2 为空返回-1
     * 2.删除时
     * 如果Stack2不为空 可以直接POP就是头部值
     * 如果Stack2为空  一个while循环内 Stack2.push(stack1.pop) 把Stack1转移到Stack2
     *
     *
     */
    public static class CQueue {

        private Stack<Integer> stack1 = new Stack<>();

        private Stack<Integer> stack2 = new Stack<>();


        public CQueue() {

        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if(stack2.isEmpty()){
                if(stack1.isEmpty()){
                    return -1;
                }
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        public int deleteHead2() {
            while (!stack1.isEmpty()){
                Integer item = stack1.pop();
                stack2.push(item);
            }
            if(stack2.isEmpty()){
                return -1;
            }
            Integer deleteItem = stack2.pop();
            while (!stack2.isEmpty()){
                Integer item = stack2.pop();
                stack1.push(item);
            }
            return deleteItem;
        }
    }


    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
     *
     * 思路 取的效率要为O(1)得通过 数组下标或者哈希表来做底层存储
     *
     * 用另外的栈存储最小值
     *
     */
    public static class MinStack {

        private Deque<Integer> mStack;

        private Deque<Integer> mMinStack;

        /** initialize your data structure here. */
        public MinStack() {
            mStack = new LinkedList<Integer>();
            mMinStack =  new LinkedList<Integer>();
            mMinStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            mStack.push(x);
            int min = Math.min(x,mMinStack.peek()); //每次push进去 计算新的最小值插入栈顶
            mMinStack.push(min);
        }

        public void pop() {
            mStack.pop();
            mMinStack.pop();
        }

        public int top() {
            return mStack.peek();
        }

        public int min() {
            return mMinStack.peek();
        }
    }
}
