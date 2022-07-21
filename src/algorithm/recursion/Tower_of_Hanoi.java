package algorithm.recursion;

import java.util.ArrayList;

/**
 * 汉诺塔问题
 * <p>
 * 描述
 * 我们有由底至上为从大到小放置的 n 个圆盘，和三个柱子（分别为左/中/右即left/mid/right），开始时所有圆盘都放在左边的柱子上，按照汉诺塔游戏的要求我们要把所有的圆盘都移到右边的柱子上，要求一次只能移动一个圆盘，而且大的圆盘不可以放到小的上面。
 * <p>
 * 请实现一个函数打印最优移动轨迹。
 * <p>
 * 给定一个 `int n` ，表示有 n 个圆盘。请返回一个 `string` 数组，其中的元素依次为每次移动的描述。描述格式为： `move from [left/mid/right] to [left/mid/right]`。
 * <p>
 * 数据范围：1\le n \le 161≤n≤16
 * 要求：时间复杂度 O(3^n)O(3n) ， 空间复杂度 O(3^n)O(3n)
 * <p>
 * 示例1
 * 输入：
 * 2
 * 返回值：
 * ["move from left to mid","move from left to right","move from mid to right"]
 * <p>
 * 牛客：https://www.nowcoder.com/practice/7d6cab7d435048c4b05251bf44e9f185?tpId=196&tqId=37559&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26pageSize%3D50%26search%3D%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D196&difficulty=undefined&judgeStatus=undefined&tags=591&title=
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/17 17:03
 */
public class Tower_of_Hanoi {

    public static void main(String[] args) {
        Tower_of_Hanoi app = new Tower_of_Hanoi();
        app.getSolution(3).stream().forEach(System.out::println);
    }

    public ArrayList<String> getSolution(int n) {
        // write code here
        if (n <= 0) {
            return null;
        }
        ArrayList<String> step = new ArrayList();
        return move(n, "left", "right", "mid", step);
    }

    public ArrayList<String> move(int n, String from, String to, String other,
                                  ArrayList<String> step) {
        if (n == 0) {
            return step;
        }
        move(n - 1, from, other, to, step);
        step.add(String.format("move from %s to %s", from, to));
        move(n - 1, other, to, from, step);
        return step;
    }
}
