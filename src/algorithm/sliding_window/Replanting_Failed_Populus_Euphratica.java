package algorithm.sliding_window;

import java.util.Scanner;

/**
 * 标题：补种未成活胡杨 | 时间限制：1秒 | 内存限制：262144K
 * 近些年来，我国防沙治沙取得显著成果。某沙漠新种植N棵胡杨（编号1-N），排成一排。一个月后，有M棵胡杨未能成活。
 * 现可补种胡杨K棵，请问如何补种（只能补种，不能新种），可以得到最多的连续胡杨树？
 * 输入描述:
 * N 总种植数量 1<=N<=100000 M 未成活胡杨数量 1<=M<=N M 个空格分隔的数，按编号从小到大排列 K 最多可以补种的数量 0<=K<=M
 * 输出描述:
 * 最多的连续胡杨棵树
 * 示例1
 * 输入
 * 5
 * 2
 * 2 4
 * 1
 * 输出
 * 3
 * 说明
 * 补种到2或4结果一样，最多的连续胡杨棵树都是3
 * 示例2
 * 输入
 * 10
 * 3
 * 2 4 7
 * 1
 * 输出
 * 6
 * 说明
 * 补种第7棵树，最多的连续胡杨棵树为6(5,6,7,8,9,10)
 * <p>
 * https://www.peiluming.com/article/90
 * <p>
 * 题目类型：
 * 1. 滑动窗口
 *
 * 类似题目：
 * 1. 最大子数组
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/21 11:38
 */
public class Replanting_Failed_Populus_Euphratica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 种植总数
        int N = Integer.parseInt(scanner.nextLine());
        // 未成活数
        int M = Integer.parseInt(scanner.nextLine());

        // 未成活位置
        String[] m_pos_str = scanner.nextLine().split(" ");
        int[] m_pos = new int[M];
        for (int i = 0; i < m_pos_str.length; i++) {
            m_pos[i] = Integer.parseInt(m_pos_str[i]);
        }
        // 补种棵树
        int K = Integer.parseInt(scanner.nextLine());
        /*
         * 10棵树 4棵未成活 补种3棵
         * 1101101001
         * 0123456789
         *
         * 20201001
         * */
        // 开始补种 滑动窗口，首先知道补种也必须是连续的才有可能获得最大连续棵树，然后设定左右指针，保证中间有K棵树补种

        // 第一个滑动值
        int sum = m_pos[K] - 1;
        int max = sum;
        // i....K+i 之间的K个坑位全部齐
        for (int i = 0; i < m_pos.length - K; i++) {
            // 记录本次 最大连续棵树
            sum = m_pos[K + i] - 1 - m_pos[i];
            // 记录历史滑动过程中的最大值
            max = Math.max(sum, max);
        }

        System.out.println(max);
    }
}
