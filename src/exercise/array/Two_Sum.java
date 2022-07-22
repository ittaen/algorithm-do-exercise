package exercise.array;

import algorithm.sort.base.RandomArrayGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * <p>
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * <p>
 * https://leetcode.cn/problems/two-sum/
 * <p>
 * https://leetcode.cn/problems/two-sum/solution/shu-zu-ni-huan-zai-yong-bao-li-fa-jie-li-6w93/
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/22 11:03
 */
public class Two_Sum {
    public static void main(String[] args) {
        //int[] arr = RandomArrayGenerator.generateRandomArray(10, 20);
        //int target = RandomArrayGenerator.getRandomNumInArray(20, 1.0, arr, 1.0) + RandomArrayGenerator.getRandomNumInArray(20, 1.0, arr, 1.0);
        int[] arr = RandomArrayGenerator.generateArray("[3,2,4]");
        int target = 6;
        Two_Sum app = new Two_Sum();
        RandomArrayGenerator.printArray(arr);
        System.out.println(target);
        RandomArrayGenerator.printArray(app.twoSum(arr, target));
    }

    public int[] twoSum(int[] nums, int target) {
        return solution(nums, target);
    }

    /**
     * 使用哈希表，减少时间复杂度
     * @param arr
     * @param target
     * @return
     */
    private int[] solution(int[] arr, int target) {
        if (arr == null || arr.length < 2) return new int[0];
        int N = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int first = 0; first < N; first++) {
            if (map.containsKey(target-arr[first])) {
                return new int[]{map.get(target-arr[first]), first};
            }
            map.put(arr[first], first);
        }
        return new int[0];
    }

    /**
     * 枚举所有情况，暴力求解
     * @param arr
     * @param target
     * @return
     */
    private int[] solution1(int[] arr, int target) {
        if (arr == null || arr.length == 0) return new int[0];
        int N = arr.length;
        for (int first = 0; first < N - 1; first++) {
            for (int next = first + 1; next < N; next++) {
                if (arr[first] + arr[next] == target) {
                    return new int[]{first, next};
                }
            }
        }
        return new int[0];
    }

}
