package exercise.array;

import algorithm.sort.base.RandomArrayGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 * <p>
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 *
 * <p>
 * https://leetcode.cn/problems/4sum
 * <p>
 * [0,0,0,1000000000,1000000000,1000000000,1000000000] [0,0,0,-1000000000,-1000000000,-1000000000,-1000000000] 一个int下溢出，一个int上溢出
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/22 14:57
 */
public class Four_Sum {
    public static void main(String[] args) {
        //int[] arr = RandomArrayGenerator.generateArray("[1,0,-1,0,-2,2]");
        //int[] arr = RandomArrayGenerator.generateArray("[2,2,2,2,2]");
        //int[] arr = RandomArrayGenerator.generateArray("[0,0,0,0]");
        // int[] arr = RandomArrayGenerator.generateArray("[0,0,0,1000000000,1000000000,1000000000,1000000000]");
        int[] arr = RandomArrayGenerator.generateArray("[1000000000,1000000000,1000000000,1000000000]");
        Four_Sum app = new Four_Sum();
        Arrays.sort(arr);
        RandomArrayGenerator.printArray(arr);
        System.out.println(app.fourSum(arr, -294967296));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        return solution(nums, target);
    }

    private List<List<Integer>> solution(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (arr == null || arr.length < 4) return res;

        Arrays.sort(arr);
        int N = arr.length;
        for (int i = 0; i < N - 3; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            // 当前的最小数 > target ,后面的情况则不用考虑了
            if (sum(arr[i], arr[i + 1], arr[i + 2], arr[i + 3]) > target) break;
            // 当前最大数 < target , 进行下一轮
            if (sum(arr[i], arr[N - 3], arr[N - 2], arr[N - 1]) < target) continue;

            for (int j = i + 1; j < N - 2; j++) {
                if (j > i + 1 && arr[j] == arr[j - 1]) continue;

                // 当前的最小数 > target ,后面的情况则不用考虑了
                if (arr[j] + arr[j + 1] > target - arr[j + 2] - arr[i]) break;
                // 当前最大数 < target , 进行下一轮
                if (arr[j] + arr[N - 2] < target - arr[N - 1] - arr[i]) continue;

                // 定位好前两层循环，开始用双指针

                int left = j + 1, right = N - 1;
                while (left < right) {
                    long sum = sum(arr[i], arr[j], arr[left], arr[right]);
                    if (sum == target) {
                        res.add(Arrays.asList(arr[i], arr[j], arr[left], arr[right]));
                        left++;
                        while (left < right && arr[left] == arr[left - 1]) {
                            left++;
                        }
                        right--;
                        while (left < right && arr[right] == arr[right + 1]) {
                            right--;
                        }
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }

                }
            }
        }
        return res;
    }

    private long sum(int a, int b, int c, int d) {
        return (long) a + (long) b + (long) c + (long) d;
    }

}
