package exercise.array;

import algorithm.sort.base.RandomArrayGenerator;

import java.util.Arrays;
import java.util.Stack;

/**
 * 每日温度
 * <p>
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * <p>
 * 示例 1:
 * <p>
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * <p>
 * 示例 2:
 * <p>
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * <p>
 * 示例 3:
 * <p>
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 * <p>
 * https://leetcode.cn/problems/daily-temperatures
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/22 17:22
 */
public class Daily_Temperatures {
    public static void main(String[] args) {
        int[] arr = RandomArrayGenerator.generateArray("[73,74,75,71,69,72,76,73]");
        Daily_Temperatures app = new Daily_Temperatures();
        RandomArrayGenerator.printArray(arr);
        RandomArrayGenerator.printArray(app.dailyTemperatures(arr));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        return solution(temperatures);
    }

    /**
     * 利用栈
     *
     * @param temperatures
     * @return
     */
    private int[] solution(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[0];
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < temperatures.length; i++) {
            // 找前面的温度值比现在的温度值低的
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                Integer preIndex = stack.pop();
                // 更新之前的 等待天数
                res[preIndex] = i - preIndex;
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 暴力解法
     *
     * @param temperatures
     * @return
     */
    private int[] solution1(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[0];
        int[] res = new int[temperatures.length];
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        int N = temperatures.length;
        for (int i = N - 1; i >= 0; i--) {
            int warmerIndex = Integer.MAX_VALUE;
            // 获取更高温度的最小下标
            for (int t = temperatures[i] + 1; t <= 100; t++) {
                if (next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            if (warmerIndex < Integer.MAX_VALUE) res[i] = warmerIndex - i;
            next[temperatures[i]] = i;
        }
        return res;
    }

}
