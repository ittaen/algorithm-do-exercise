package linked_list;

import linked_list.base.ListNode;
import linked_list.util.LinkedListGenerator;

import java.util.HashSet;

/**
 * 判断链表中是否有环
 * <p>
 * 判断给定的链表中是否有环。如果有环则返回true，否则返回false。
 * <p>
 * 数据范围：链表长度 0≤n≤10000，链表中任意节点的值满足 |val| <= 100000∣
 * 要求：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * <p>
 * 输入分为两部分，第一部分为链表，第二部分代表是否有环，
 * 然后将组成的head头结点传入到函数里面。-1代表无环，其它的数字代表有环，
 * 这些参数解释仅仅是为了方便读者自测调试。实际在编程时读入的是链表的头节点。
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/18 13:09
 */
public class Has_Cycle {

    public static void main(String[] args) {
        Has_Cycle app = new Has_Cycle();
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            ListNode listNode = LinkedListGenerator.generateRandomCycleListNode(1_0000, 10_0000);
            System.out.println(app.hasCycle(listNode));
        }
    }

    public boolean hasCycle(ListNode head) {
        return solution3(head);
    }

    /**
     * 采用双指针
     *
     * @param head
     * @return
     */
    public boolean solution1(ListNode head) {
        boolean ans = false;
        ListNode fast = head;
        ListNode slow = head;
        while (slow != null && fast != null) {
            if (fast.next == null) {
                break;
            }
            fast = fast.next.next;
            if (fast == slow) {
                ans = true;
                break;
            }
            slow = slow.next;
        }
        return ans;
    }

    /**
     * 采用哈希表
     *
     * @param head
     * @return
     */
    public boolean solution2(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 节点删除
     *
     * @param head
     * @return
     */
    public boolean solution3(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            if (head.next == head) {
                return true;
            }
            pre = head;
            head = head.next;
            pre.next = pre;
        }
        return false;
    }

}
