package linked_list;

import linked_list.base.ListNode;
import linked_list.util.LinkedListGenerator;

/**
 * 删除有序链表中重复的元素-I
 * <p>
 * 删除给出链表中的重复元素（链表中元素从小到大有序），使链表中的所有元素都只出现一次
 * 例如：
 * 给出的链表为 1→1→2,返回 1→2.
 * 给出的链表为 1→1→2→3→3,返回 1→2→3.
 * 数据范围：链表长度满足 0≤n≤100，链表中任意节点的值满足 ∣val∣≤100
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * <p>
 * 其他解决思路：
 * 1. 结合指针跳过的思路可以改成删除的做法，其实指针跳过就是删除
 *
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/19 12:48
 */
public class Delete_Duplicates_I {
    public static void main(String[] args) {
        Delete_Duplicates_I app = new Delete_Duplicates_I();
        ListNode listNode = LinkedListGenerator.generateListNode("{1,1,2}");
        LinkedListGenerator.println(listNode);
        LinkedListGenerator.println(app.deleteDuplicates(listNode));
    }

    public ListNode deleteDuplicates(ListNode head) {
        return solution(head);
    }

    /**
     * 递归
     * @param head
     * @return
     */
    private ListNode solution(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = solution(head.next);
        if (head.val == head.next.val) {
            head = head.next;
        }
        return head;
    }

    /**
     * 碰到相等的就跳过
     * @param head
     * @return
     */
    private ListNode solution1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

}
