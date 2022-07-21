package data_structure.linked_list;

import data_structure.linked_list.base.ListNode;
import data_structure.linked_list.util.LinkedListGenerator;

/**
 * 单链表的排序
 * <p>
 * 给定一个节点数为n的无序单链表，对其按升序排序。
 * 数据范围：0<n≤100000
 * 要求：时间复杂度 O(nlogn)O(nlogn)
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/19 16:09
 */
public class Sort_In_List {
    public static void main(String[] args) {
        Sort_In_List app = new Sort_In_List();
        ListNode listNode = LinkedListGenerator.generateListNode("{65,72,53,74,39,47,56,82,78,6,38,56,6,65,77}");
//        ListNode listNode = LinkedListGenerator.generateListNode(50, 100);
        LinkedListGenerator.println(listNode);
        LinkedListGenerator.println(app.sortInList(listNode));
    }

    public ListNode sortInList(ListNode head) {
        return solution(head);
    }

    private ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode[] midAndTail = getMidAndTailNode(head, null);
        return divideAndMerge(head, midAndTail[1]);
    }

    private ListNode divideAndMerge(ListNode start, ListNode end) {
        if (start == end) {
            return start;
        }
        ListNode[] arr = getMidAndTailNode(start, end);
        ListNode mid = arr[0];
        ListNode next = mid.next;
        // 切断链表
        mid.next = null;
        start = divideAndMerge(start, mid);
        next = divideAndMerge(next, end);
        return merge(start, next);
    }

    private ListNode merge(ListNode start1, ListNode start2) {
        ListNode head = start1.val < start2.val ? start1 : start2;
        ListNode next = head;
        ListNode cur1 = head.next;
        ListNode cur2 = head == start1 ? start2 : start1;
        while (cur1 != null && cur2 != null) {
            next.next = cur1.val < cur2.val ? cur1 : cur2;
            next = next.next;
            if (next == cur1) {
                cur1 = cur1.next;
            } else {
                cur2 = cur2.next;
            }
        }
        while (cur1 != null) {
            next.next = cur1;
            next = next.next;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            next.next = cur2;
            next = next.next;
            cur2 = cur2.next;
        }
        return head;
    }

    /**
     * 快慢指正获取中间节点和尾节点
     * [start, end]
     *
     * @param start
     * @param end
     * @return
     */
    private ListNode[] getMidAndTailNode(ListNode start, ListNode end) {
        if (start == null || start.next == null) {
            return new ListNode[]{start, start};
        }
        ListNode fast = start;
        ListNode slow = start;
        if (end != null) {
            end = end.next;
        }
        while (fast.next != end && fast.next != null) {
            // 偶数个
            if (fast.next.next == end || fast.next.next == null) {
                // 保留最后一个节点位置
                fast = fast.next;
                //slow = slow.next; //取中间位置的前一个
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return new ListNode[]{slow, fast};
    }

}
