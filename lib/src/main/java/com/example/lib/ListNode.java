package com.example.lib;

public class ListNode {
         int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public void printNode(){
        System.out.println(val+",");
        if(this.next!=null){
            next.printNode();
        }
    }
}
