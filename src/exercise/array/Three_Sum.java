package exercise.array;

import algorithm.sort.base.RandomArrayGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * <p>
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * <p>
 * 链接：https://leetcode.cn/problems/3sum
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/22 13:05
 */
public class Three_Sum {
    public static void main(String[] args) {
        int[] arr = RandomArrayGenerator.generateArray("[-1,0,1,2,-1,-4]");
        Three_Sum app = new Three_Sum();
        Arrays.sort(arr);
        RandomArrayGenerator.printArray(arr);
        System.out.println(app.threeSum(arr));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        return solution(nums);
    }

    private List<List<Integer>> solution(int[] arr) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (arr == null || arr.length < 3) return res;

        int N = arr.length;
        // 排序
        Arrays.sort(arr);
        // 第一个数的范围 0..N-1
        for (int first = 0; first < N; first++) {
            // 下一个数与上一个重复
            if (first > 0 && arr[first] == arr[first - 1]) continue;
            // 第三个数从最右边开始
            int third = N - 1;
            // 第二个数从第一个数的下一个开始
            for (int second = first + 1; second < third; second++) {
                // 下一个数与上一个重复
                if (second > first + 1 && arr[second] == arr[second - 1]) continue;
                // 调整第三个指针,同时要保证third在second右边
                while (second < third - 1 && arr[first] + arr[second] + arr[third] > 0) {
                    third--;
                }
                if (arr[first] + arr[second] + arr[third] == 0) {
                    res.add(Arrays.asList(arr[first], arr[second], arr[third]));
                }
            }
        }
        return res;
    }
}
