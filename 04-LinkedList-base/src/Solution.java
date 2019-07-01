

//leetcode 203
public class Solution {
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "val="+val+",next="+next;
        }
    }

    /**
     * 删除链表中值为指定值的元素
     * @param head 链表的头结点指针
     * @param val 指定值
     * @return 返回删除指定元素后的链表
     */
    //使用虚拟头结点,可以统一逻辑，不必处理特殊情况
    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while(prev.next != null) {
            if(prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = prev.next.next;
                delNode.next = null;
                //删除后，prev不移动!!!
            } else {
                prev = prev.next;
            }
        }
//        return head;//注意返回值！！！！
        return dummyHead.next;
    }

    /**
     * 删除链表中值为指定值的元素we
     * @param head 链表的头结点指针
     * @param val 指定值
     * @return 返回删除指定元素后的链表
     */
    //不设计头结点
    public ListNode removeElements_1(ListNode head, int val) {

        while(head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        //空链表
        if(head == null) {
            return head;
        }

        ListNode prev = head;
        while(prev.next != null) {
            if(prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = prev.next.next;
                delNode.next = null;
                //删除后，prev不移动!!!
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    /**
     * 删除链表中值为指定值的元素we
     * @param head 链表的头结点指针
     * @param val 指定值
     * @return 返回删除指定元素后的链表
     */
    //不设计头结点
    public ListNode removeElements_0(ListNode head, int val) {
        //1)空链表
        if(head == null) {
            return null;
        } else {

            while(head != null && head.val == val) {
                ListNode delNode = head;
                head = head.next;
                delNode.next = null;
            }

            //2)空链表
            //1)与2)可以合并
            if(head == null) {
                return head;
            }

            ListNode prev = head;
            while(prev.next != null) {
                if(prev.next.val == val) {
                    ListNode delNode = prev.next;
                    prev.next = prev.next.next;
                    delNode.next = null;
                    //删除后，prev不移动!!!
                } else {
                    prev = prev.next;
                }
            }
            return head;
        }
    }

}

