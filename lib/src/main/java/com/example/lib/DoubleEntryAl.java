package com.example.lib;

import com.example.lib.bean.ListNode;

public class DoubleEntryAl {

    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     *
     * 返回删除后的链表的头节点。
     *
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     *
     * 输入: head = [4,5,1,9], val = 1
     * 输出: [4,5,9]
     * 解释: 给定你链表中值为1的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
     *
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode deleteNode(ListNode head, int val) {
        ListNode now = head;
        if(now.val == val){
            return now.next;
        }
        while(now.next!=null){
            if(now.next.val == val){
                now.next = now.next.next;
                return head;
            }
            now = now.next;
        }
        return null;
    }


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
