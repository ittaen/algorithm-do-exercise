package linked_list;

import linked_list.base.ListNode;
import linked_list.util.LinkedListGenerator;

/**
 * 两个链表的第一个公共结点
 * <p>
 * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
 * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 * 数据范围： n≤1000
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/19 15:21
 */
public class Find_First_Common_Node {
    public static void main(String[] args) {
        Find_First_Common_Node app = new Find_First_Common_Node();
        ListNode[] arr = LinkedListGenerator.generateHasCommonNodeList("{1,2,3},{4,5},{6,7}");
        LinkedListGenerator.println(arr[0]);
        LinkedListGenerator.println(arr[1]);
        LinkedListGenerator.println(app.FindFirstCommonNode(arr[0], arr[1]));
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        return solution(pHead1, pHead2);
    }

    private ListNode solution(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        int len1 = getLen(pHead1);
        int len2 = getLen(pHead2);
        ListNode lHead = len1 > len2 ? pHead1 : pHead2;
        ListNode sHead = lHead == pHead1 ? pHead2 : pHead1;
        // 长链表先走
        int diff = Math.abs(len1 - len2);
        while (diff > 0) {
            lHead = lHead.next;
            diff--;
        }
        while (lHead != sHead) {
            lHead = lHead.next;
            sHead = sHead.next;
        }
        return lHead;
    }

    private int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }


    private ListNode solution1(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ListNode cur1 = pHead1;
        ListNode cur2 = pHead2;
        while (cur1 != cur2) {
            cur1 = cur1 == null ? pHead2 : cur1.next;
            cur2 = cur2 == null ? pHead1 : cur2.next;
        }
        return cur1;
    }

}
