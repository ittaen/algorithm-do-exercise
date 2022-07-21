package data_structure.linked_list;

import data_structure.linked_list.base.ListNode;
import data_structure.linked_list.util.LinkedListGenerator;

/**
 * 删除链表的倒数第n个节点
 * <p>
 * 给定一个链表，删除链表的倒数第 n 个节点并返回链表的头指针
 * 例如，
 * 给出的链表为: 1→2→3→4→5,n=2.
 * 删除了链表的倒数第 n 个节点之后,链表变为 1→2→3→5.
 * <p>
 * 数据范围： 链表长度 0≤n≤1000，链表中任意节点的值满足 0≤val≤100
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * 备注：
 * 题目保证 nn 一定是有效的
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/18 20:42
 */
public class Remove_Nth_From_End {
    public static void main(String[] args) {
        Remove_Nth_From_End app = new Remove_Nth_From_End();
        ListNode listNode = LinkedListGenerator.generateListNode("{1,2}");
        LinkedListGenerator.println(app.removeNthFromEnd(listNode, 2));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return solution(head, n);
    }

    private ListNode solution(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        for (int i = 1; i < n && first != null; i++) {
            first = first.next;
        }
        ListNode pre = null;
        while (first != null && first.next != null) {
            first = first.next;
            pre = second;
            second = second.next;
        }
        if (second != null) {
            if (pre == null) {
                head = head.next;
            } else {
                pre.next = second.next;
            }
        }
        return head;
    }

}
