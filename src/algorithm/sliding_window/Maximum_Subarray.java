package algorithm.sliding_window;

import algorithm.sort.base.RandomArrayGenerator;

/**
 * Maximum Subarray
 * 最大子数组
 * <p>
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * A subarray is a contiguous part of an array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * <p>
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 * <p>
 * https://leetcode.com/problems/maximum-subarray/
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/21 9:05
 */
public class Maximum_Subarray {
    public static void main(String[] args) {
        Maximum_Subarray app = new Maximum_Subarray();
        // [-2,1,-3,4,-1,2,1,-5,4]
        // [-2,1,-3,4,-1,2,1,-5,4]
        int[] arr = RandomArrayGenerator.generateArray("[1]");
        RandomArrayGenerator.printArray(arr);
        System.out.println(app.maxSubArray(arr));;
    }

    public int maxSubArray(int[] nums) {
        return solution(nums);
    }

    private int solution(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = arr[0];
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre = Math.max(arr[i], arr[i] + pre);
            max = Math.max(pre, max);
        }
        return max;
    }
}
