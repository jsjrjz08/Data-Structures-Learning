
//使用递归方法实现
//leetcode 203
public class Solution3 {

    /**
     * 宏观语义:删除链表中值为指定值的元素，并返回处理后的链表
     * @param head 链表的头结点引用
     * @param val 指定值
     * @return 返回删除指定元素后的链表
     */
    public ListNode removeElements(ListNode head, int val) {

        if(head == null) {
            return null;
        }

        head.next = removeElements(head.next,val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {//不能提交leetcode，因为leetcode上ListNode的构造方法是不存在的，不能通过编译!
        int[] arr = {6,6,2,1,5,6,3,6,7};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        System.out.println((new Solution3()).removeElements(head,6));

    }

}

