package data_structure.linked_list;

import data_structure.linked_list.base.ListNode;
import data_structure.linked_list.util.LinkedListGenerator;

/**
 * 链表的奇偶重排
 * <p>
 * 给定一个单链表，请设定一个函数，将链表的奇数位节点和偶数位节点分别放在一起，重排后输出。
 * 注意是节点的编号而非节点的数值。
 * 数据范围：节点数量满足 0≤n≤10^5
 * ，节点中的值都满足 0≤val≤1000
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * <p>
 * {1,2,3,4,5,6}
 * {2,1,3,5,6,4,7}
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/19 10:25
 */
public class Odd_Even_List {
    public static void main(String[] args) {
        Odd_Even_List app = new Odd_Even_List();
        ListNode listNode = LinkedListGenerator.generateListNode("{2,1,3,5,6,4,7}");
        LinkedListGenerator.println(listNode);
        LinkedListGenerator.println(app.oddEvenList(listNode));
    }

    public ListNode oddEvenList(ListNode head) {
        return solution(head);
    }

    /**
     * 双指针，每次一个节点
     * @param head
     * @return
     */
    private ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;
        ListNode cur = head.next.next;
        int index = 1;
        while (cur != null) {
            if (index % 2 == 1) {
                odd.next = cur;
                odd = cur;
            } else {
                even.next = cur;
                even = cur;
            }
            cur = cur.next;
            index++;
        }
        // 如果最后一个节点是偶数，则next应为null
        if (even.next != null) {
            even.next = null;
        }
        odd.next = evenHead;
        return head;
    }

    /**
     * 双指针，同时进行
     * @param head
     * @return
     */
    private ListNode solution1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;
        // 保证了偶数位不会停在null位置
        while (even.next != null && even.next.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        if (odd.next != null && odd.next.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
        }
        // 如果是奇数结尾，则把偶数最后一个节点的next置空
        if (odd.next == null) {
            even.next = null;
        }
        odd.next = evenHead;
        return head;
    }

}
