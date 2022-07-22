package exercise.array;

import algorithm.sort.base.RandomArrayGenerator;

/**
 * 跳跃游戏 II
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 *
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 *
 * <p>
 * https://leetcode.cn/problems/jump-game-ii
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/22 17:09
 */
public class Jump_II {
    public static void main(String[] args) {
        int[] arr = RandomArrayGenerator.generateArray("[2,3,1,1,4]");
        Jump_II app = new Jump_II();
        RandomArrayGenerator.printArray(arr);
        System.out.println(app.jump(arr));
    }

    public int jump(int[] nums) {
        return solution(nums);
    }

    /**
     * 贪心算法，正向遍历
     *
     * @param nums
     * @return
     */
    private int solution(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int N = nums.length;
        int maxPosition = 0, step = 0, end = 0;
        for (int i = 0; i < N - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (end == i) {
                end = maxPosition;
                step++;
            }
        }
        return step;
    }

    /**
     * 贪心算法，反向遍历
     *
     * @param nums
     * @return
     */
    private int solution1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int N = nums.length;
        int position = N - 1, step = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    step++;
                }
            }
        }
        return step;
    }

}
