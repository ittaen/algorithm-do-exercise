package linked_list.util;

import linked_list.base.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 链表生成器
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/18 13:28
 */
public class LinkedListGenerator {

    /**
     * 生成多条链表
     *
     * @param str [{-9},{},{-10,-8,-2,-1,2},{-10,-9,-7,-6,-5,-2,-2,1,4},{-7,-7,-1,0,4},{},{-4,0},{-9,-6,-5,-5,-2,2,3,3}]
     * @return
     */
    public static List<ListNode> generateMultiList(String str) {
        str = str.replace("[", "")
                .replace("]", "");
        ArrayList<ListNode> list = (ArrayList<ListNode>) Stream.of(str.split("},\\{")).map(s -> {
            s = s.replace("{", "")
                    .replace("}", "");
            String[] nums = s.split(",");
            ListNode head = null;
            if (nums.length == 1 && nums[0].equals("")) {
                return head;
            }
            if (nums.length > 0) {
                head = new ListNode(Integer.parseInt(nums[0]));
                ListNode last = head;
                for (int i = 1; i < nums.length; i++) {
                    ListNode cur = new ListNode(Integer.parseInt(nums[i]));
                    last.next = cur;
                    last = cur;
                }
                Stream.of(nums).map(s1 -> new ListNode(Integer.parseInt(s1))).collect(Collectors.toList());
            }
            return head;
        }).collect(Collectors.toList());
        return list;
    }

    public static ListNode generateRandomCycleListNode(int maxSize, int maxValue) {
        double value = Math.random();
        if (value < 0.5) {
            return generateCycleListNode(maxSize, maxValue);
        } else {
            return generateListNode(maxSize, maxValue);
        }
    }

    public static ListNode generateCycleListNode(int maxSize, int maxValue) {
        return generateCycleListNode(maxSize, maxValue, true);
    }

    public static ListNode generateCycleListNode(int maxSize, int maxValue, boolean isCycle) {
        int size = (int) (maxSize * Math.random());
        ListNode head = null;
        if (size > 0) {
            head = new ListNode((int) (maxValue * Math.random()));
            ListNode cur = head;
            int chooseIndex = (int) (size * Math.random());
            ListNode choose = null;
            for (int i = 0; i < size; i++) {
                cur.next = new ListNode((int) (maxValue * Math.random()));
                cur = cur.next;
                if (isCycle && i == chooseIndex) {
                    choose = cur;
                }
                if (isCycle && i == size - 1) {
                    cur.next = choose;
                }
            }
        }
        return head;
    }

    public static ListNode generateListNode(int maxSize, int maxValue) {
        return generateCycleListNode(maxSize, maxValue, false);
    }

}
