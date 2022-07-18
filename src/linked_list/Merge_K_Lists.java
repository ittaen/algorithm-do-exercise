package linked_list;

import linked_list.base.ListNode;
import linked_list.util.LinkedListGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并k个已排序的链表
 * https://www.nowcoder.com/practice/65cfde9e5b9b4cf2b6bafa5f3ef33fa6?tpId=295&tqId=724&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D295
 * <p>
 * 合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
 * 数据范围：节点总数满足 0 \le n \le 10^50≤n≤10^5，
 * 链表个数满足 1 \le k \le 10^5 \1≤k≤10^5，
 * 每个链表的长度满足 1 \le len \le 200 \1≤len≤200，
 * 每个节点的值满足 |val| <= 1000
 * 要求：时间复杂度 O(nlogk)
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/18 11:17
 */
public class Merge_K_Lists {

    public static void main(String[] args) {
        String str = "[{-9},{},{-10,-8,-2,-1,2},{-10,-9,-7,-6,-5,-2,-2,1,4},{-7,-7,-1,0,4},{},{-4,0},{-9,-6,-5,-5,-2,2,3,3}]";
        ArrayList<ListNode> list = (ArrayList) LinkedListGenerator.generateMultiList(str);
        Merge_K_Lists app = new Merge_K_Lists();
        ListNode listNode = app.mergeKLists(list);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                heap.add(list);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        ListNode head = heap.poll();
        ListNode tail = head;
        if (tail.next != null) {
            heap.add(tail.next);
        }
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            tail.next = min;
            tail = min;
            if (min.next != null) {
                heap.add(min.next);
            }
        }
        return head;
    }

    public static ListNode mergeKLists2(ArrayList<ListNode> lists) {
        if (lists == null) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                heap.add(lists.get(i));
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }

}
