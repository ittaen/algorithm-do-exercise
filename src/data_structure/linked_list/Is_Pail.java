package data_structure.linked_list;

import data_structure.linked_list.base.ListNode;
import data_structure.linked_list.util.LinkedListGenerator;

/**
 * 判断一个链表是否为回文结构
 * <p>
 * 给定一个链表，请判断该链表是否为回文结构。
 * 回文是指该字符串正序逆序完全一致。
 * 数据范围： 链表节点数 0≤n≤10^5
 * ，链表中每个节点的值满足 ∣val∣≤10^7
 * <p>
 * 当前采用双指针方法
 * 其他解决方法：
 * 1. 申请一个ArrayList，可以用到数组的索引去做
 *
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/19 9:59
 */
public class Is_Pail {
    public static void main(String[] args) {
        Is_Pail app = new Is_Pail();
        ListNode listNode = LinkedListGenerator.generateListNode("{1,2,1}");
        System.out.println(app.isPail(listNode));
    }

    public boolean isPail(ListNode head) {
        return solution(head);
    }

    /**
     * 双指针
     *
     * @param head
     * @return
     */
    private boolean solution(ListNode head) {
        // 空链表默认为回文结构
        if (head == null || head.next == null) {
            return true;
        }
        // 利用快慢指针找中点位置
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 节点为奇数，则fast来到最后不为空的位置
        if (fast != null) {
            slow = slow.next;
        }
        // 翻转 中点以后的位置
        slow = LinkedListGenerator.reverse(slow);
        // 比较前半部分和翻转后的部分
        while (head!=null && slow!=null) {
            if (head.val!=slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

}
