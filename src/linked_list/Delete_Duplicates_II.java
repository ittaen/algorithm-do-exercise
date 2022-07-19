package linked_list;

import linked_list.base.ListNode;
import linked_list.util.LinkedListGenerator;

/**
 * 删除有序链表中重复的元素-II
 * <p>
 * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 * 例如：
 * 给出的链表为 1→2→3→3→4→4→5, 返回 1→2→5.
 * 给出的链表为 1→1→1→2→3, 返回 2→3.
 * 数据范围：链表长度 0≤n≤10000，链表中的值满足 ∣val∣≤1000
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * <p>
 * {1,1,2,2,3}
 * <p>
 * 其他解题方法：
 * 1. 哈希表
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/19 13:09
 */
public class Delete_Duplicates_II {
    public static void main(String[] args) {
        Delete_Duplicates_II app = new Delete_Duplicates_II();
        ListNode listNode = LinkedListGenerator.generateListNode("{1,2,2}");
        LinkedListGenerator.println(listNode);
        LinkedListGenerator.println(app.deleteDuplicates(listNode));
    }

    public ListNode deleteDuplicates(ListNode head) {
        return solution(head);
    }

    /**
     * 碰到相等的就跳过
     *
     * @param head
     * @return
     */
    private ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nHead = new ListNode(-1);
        nHead.next = head;
        ListNode cur = nHead;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int val = cur.next.val;
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return nHead.next;
    }

}
