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
            if (nums.length == 1 && "".equals(nums[0])) {
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
            }
            return head;
        }).collect(Collectors.toList());
        return list;
    }

    /**
     * 生成链表
     *
     * @param str {1,2,3,4,5}
     * @return
     */
    public static ListNode generateListNode(String str) {
        str = str.replace("{", "")
                .replace("}", "");
        String[] nums = str.split(",");
        ListNode head = null;
        if (nums.length == 1 && "".equals(nums[0])) {
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
        }
        return head;
    }

    /**
     * 生成有公共节点的两条链表
     *
     * @param str {1,2,3},{4,5},{6,7}
     * @return
     */
    public static ListNode[] generateHasCommonNodeList(String str) {
        // str = "{1,2,3},{4,5},{6,7}";
        List<ListNode> collect = Stream.of(str.split("},\\{"))
                .map(LinkedListGenerator::generateListNode)
                .collect(Collectors.toList());
        ListNode list1 = collect.get(0);
        ListNode list2 = collect.get(1);
        ListNode common = collect.get(2);
        connectList(list1, common);
        connectList(list2, common);
        return new ListNode[]{list1, list2};
    }

    public static ListNode connectList(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1: head2;
        }
        ListNode cur = head1;
        while (true) {
            // 移动到最后一个节点
            if (cur.next == null) {
                cur.next = head2;
                break;
            }
            cur = cur.next;
        }
        return head1;
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

    public static void println(ListNode head) {
        System.out.print(getLen(head) + " - ");
        System.out.print(String.format(" asc(%s) - ", isSort(head, true)));
        if (head != null) {
            System.out.print("{");
            System.out.print(head.val);
            head = head.next;
        }
        while (head != null) {
            System.out.print("," + head.val);
            head = head.next;
        }
        System.out.println("}");
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public static boolean isSort(ListNode head, boolean asc) {
        if (head == null) {
            return true;
        }
        boolean ans = true;
        int min = head.val;
        int max = head.val;
        head = head.next;
        while (head != null) {
            int val = head.val;
            if (asc) {
                if (val < min) {
                    return false;
                }
            } else {
                if (val > max) {
                    return false;
                }
            }
            head = head.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode listNode = generateListNode(100, 100);
        println(listNode);
        listNode = reverse(listNode);
        println(listNode);
        String str = "{1,2,3},{4,5},{6,7}";
        generateHasCommonNodeList(str);
    }

}
