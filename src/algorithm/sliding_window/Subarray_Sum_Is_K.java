package algorithm.sliding_window;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 标题：和为 k 的子数组
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 * <p>
 * 示例 1：
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * 示例 2：
 * <p>
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 *
 * <p>
 * https://leetcode.cn/problems/QTMn0o/
 * https://leetcode.cn/problems/subarray-sum-equals-k/
 * <p>
 * 思路:
 * 1. 前缀数组
 * 2. 枚举
 * 3. 连续子数组
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/21 13:26
 */
public class Subarray_Sum_Is_K {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int x = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        // 测试数据 3 7 3 4 7
        // 测试数据 6 7 3 4 7 2 1 1
        // 暴力求解
        Subarray_Sum_Is_K app = new Subarray_Sum_Is_K();
        System.out.println(app.subarraySum(arr, x));
    }

    public int subarraySum(int[] nums, int k) {
        return solution(nums, k);
    }

    /**
     * 前缀数组+哈希表
     *
     * @param arr
     * @param k
     * @return
     */
    private int solution(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        int pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < N; i++) {
            pre += arr[i];
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    /**
     * 暴力求解
     *
     * @param arr
     * @param k
     * @return
     */
    private int solution1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        // 枚举连续子数组变化所有的情况 让左边界从0开始变化
        for (int start = 0; start < N; start++) {
            int sum = 0;
            for (int left = start; left < N; left++) {
                sum += arr[left];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

}
