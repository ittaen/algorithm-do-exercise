package data_structure.linked_list;

import data_structure.linked_list.base.ListNode;
import data_structure.linked_list.util.LinkedListGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表中环的入口结点
 * <p>
 * 给一个长度为n链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 *
 * 数据范围： n\le10000n≤10000，1<=结点值<=100001<=结点值<=10000
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/18 17:54
 */
public class Entry_Node_Of_Loop {
    public static void main(String[] args) {
        Entry_Node_Of_Loop app = new Entry_Node_Of_Loop();
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            ListNode listNode = LinkedListGenerator.generateRandomCycleListNode(1_0000, 10_0000);
            System.out.println(app.EntryNodeOfLoop(listNode));
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        return solution(pHead);
    }

    private ListNode solution(ListNode pHead) {
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (slow != null & fast != null) {
            if (fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                while (pHead != slow) {
                    pHead = pHead.next;
                    slow = slow.next;
                }
                return pHead;
            }
        }
        return null;
    }

    /**
     * 哈希表
     *
     * @param pHead
     * @return
     */
    private ListNode solution1(ListNode pHead) {
        Set<ListNode> set = new HashSet<>();
        while (pHead != null) {
            if (set.contains(pHead)) {
                return pHead;
            }
            set.add(pHead);
            pHead = pHead.next;
        }
        return null;
    }

    /**
     * 删除节点
     *
     * @param pHead
     * @return
     */
    private ListNode solution2(ListNode pHead) {
        ListNode pre;
        while (pHead != null) {
            if (pHead == pHead.next) {
                return pHead;
            }
            pre = pHead;
            pHead = pHead.next;
            pre.next = pre;
        }
        return null;
    }

}
