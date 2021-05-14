public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        //1.设置头结点
        ListNode listNode = new ListNode(0);
        listNode.next = head;

        ListNode first = head;
        //2.第一次遍历求出链表的长度length
        int len = 0;
        while (first != null) {
            first = first.next;
            len++;
        }
        //3.第二次遍历删除元素
        len = len - n;
        first = listNode;
        while (len > 0) {
            first = first.next;
            len--;
        }
        first.next = first.next.next;
        return listNode.next;
    }
}
