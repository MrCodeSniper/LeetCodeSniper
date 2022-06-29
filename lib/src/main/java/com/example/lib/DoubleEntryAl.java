package com.example.lib;

import com.example.lib.bean.ListNode;

import java.util.HashMap;

public class DoubleEntryAl {

    /**
     * 输入两个链表，找出它们的第一个公共节点。
     *
     *
     * 如下面的两个链表：
     *
     * <img width="800" height="240" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" alt="">
     *
     * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
     * 输出：Reference of the node with value = 8
     * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
     *
     *
     * 思路1  双链表循环 表a走完一次后放入map  表b走的时候需要判断是否在map中存在 第一个存在的就是香蕉节点
     * 时间复杂度O(Max(m,n)) m,n代表链表长度 空间复杂度O(Min(m,n)) 第一次循环最小长度的链表即可
     *
     * 思路2 双指针
     * 我们令第一条链表相交节点之前的长度为 a，第二条链表相交节点之前的长度为 b，相交节点后的公共长度为 c（注意 c 可能为 00，即不存在相交节点）。
     * 分别对两条链表进行遍历：
     *
     * 当第一条链表遍历完，移动到第二条链表的头部进行遍历；
     * 当第二条链表遍历完，移动到第一条链表的头部进行遍历。
     *
     * 第一条链表首次到达「第一个相交节点」的充要条件是第一条链表走了 a + c + b 步，由于两条链表同时出发，并且步长相等，
     * 因此当第一条链表走了 a + c + ba+c+b 步时，第二条链表同样也是走了 a + c + b 步，即 第二条同样停在「第一个相交节点」的位置
     *
     * 如果不存在交点：两者会在走完 a + c + b 之后同时变为 null，退出循环。
     *
     * 总会香蕉 时间复杂度O(a+c+b m+n) 空间复杂度O(1)
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode,Integer> nodeHashMap = new HashMap();
        ListNode nowA = headA;
        int index = 0;
        while (nowA!=null){
            nodeHashMap.put(nowA,index);
            nowA = nowA.next;
            index++;
        }
        ListNode nowB = headB;
        while (nowB!=null){
            Integer equalElementIndex = nodeHashMap.get(nowB);
            if(equalElementIndex!=null){
                return nowB;
            }
            nowB = nowB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode nowA = headA; ListNode nowB = headB;
        while (nowA!=nowB){
            if(nowA == null){
                nowA = headB;
            }else {
                nowA = nowA.next;
            }
            if(nowB == null){
                nowB = headA;
            }else {
                nowB = nowB.next;
            }
        }
        return nowA;
    }

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
