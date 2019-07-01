class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    //添加构造方法
    ListNode(int[] arrNums) {
        if(arrNums == null || arrNums.length == 0) {
            throw new IllegalArgumentException("arr is empty");
        }

        this.val = arrNums[0];//
        ListNode cur = this;//
        for(int i=1; i<arrNums.length; i++) {
            cur.next = new ListNode(arrNums[i]);
            cur = cur.next;
        }
    }

    //以head为头结点的链表信息
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("elements [");

        ListNode cur = this;//
        while (cur != null) {
            sb.append(cur.val+"->");
            cur = cur.next;
        }

        sb.append("NULL]");
        return sb.toString();
    }
}

//含有虚拟头结点
//本地调试，可以自己制作测试用例、debug等。修改ListNode的构造方法、添加toString方法
//leetcode 203
public class Solution2 {

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

    public static void main(String[] args) {//不能提交leetcode，因为leetcode上ListNode的构造方法是不存在的，不能通过编译!
        int[] arr = {6,6,2,1,5,6,3,6,7};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        System.out.println((new Solution2()).removeElements(head,6));

    }

}

