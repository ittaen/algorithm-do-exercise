package data_structure.linked_list;

import data_structure.linked_list.base.ListNode;
import data_structure.linked_list.util.LinkedListGenerator;

/**
 * 链表中倒数最后k个结点
 * <p>
 * 输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
 * 如果该链表长度小于k，请返回一个长度为 0 的链表。
 * <p>
 * 数据范围：0≤n≤10^5，0≤ai<10^9，0≤k≤10^9
 * <p>
 * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)
 * 进阶：空间复杂度 O(1)O(1)，时间复杂度 O(n)O(n)
 * <p>
 * 其他思路：
 * 1. 双指针，第一个指针移到第k个位置，第二个指针指向头，然后两个指针同时移动。。。
 * 2. 栈，弹入栈，再出栈k个，组成链表
 * 3. 类似于递归逆序栈的方式
 * 4. 先找链表长度，再找k位置
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/18 18:23
 */
public class Find_Kth_To_Tail {
    public static void main(String[] args) {
        Find_Kth_To_Tail app = new Find_Kth_To_Tail();
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            int maxK = (int) (Math.random() * Math.pow(10, 2));
            int maxSize = (int) (Math.random() * Math.pow(10, 2));
            int maxValue = (int) (Math.random() * Math.pow(10, 2));
//            ListNode listNode = LinkedListGenerator.generateListNode(maxSize, maxValue);
//            LinkedListGenerator.println(app.FindKthToTail(listNode, maxK));
        }
        ListNode listNode = LinkedListGenerator.generateListNode("{1,2,3,4,5}");
        LinkedListGenerator.println(app.FindKthToTail(listNode, 2));
    }

    public ListNode FindKthToTail(ListNode pHead, int k) {
        return solution(pHead, k);
    }

    /**
     * 逆序+前k个元素+逆序
     * @param pHead
     * @param k
     * @return
     */
    private ListNode solution(ListNode pHead, int k) {
        pHead = LinkedListGenerator.reverse(pHead);
        ListNode pre = null;
        ListNode next;
        while (k-- > 0) {
            if (pHead != null) return null;
            next = pHead.next;
            pHead.next = pre;
            pre = pHead;
            pHead = next;
        }
        return pre;
    }

}
