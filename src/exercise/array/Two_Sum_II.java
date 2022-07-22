package exercise.array;

import algorithm.sort.base.RandomArrayGenerator;

/**
 * 两数之和 II - 输入有序数组
 * 注意有序和无序数组的差别
 * <p>
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，
 * 请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，
 * 则 1 <= index1 < index2 <= numbers.length 。
 * <p>
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * <p>
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * <p>
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 * 示例 2：
 * <p>
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
 * 示例 3：
 * <p>
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 *
 * <p>
 * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
 * 题解：
 * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/solution/liang-shu-zhi-he-ii-shu-ru-you-xu-shu-zu-by-leet-2/
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/22 12:00
 */
public class Two_Sum_II {
    public static void main(String[] args) {
        int[] arr = RandomArrayGenerator.generateArray("[2,7,11,15]");
        int target = 9;
        Two_Sum_II app = new Two_Sum_II();
        RandomArrayGenerator.printArray(arr);
        System.out.println(target);
        RandomArrayGenerator.printArray(app.twoSum(arr, target));
    }

    public int[] twoSum(int[] numbers, int target) {
        return solution(numbers, target);
    }

    /**
     * 利用双指针
     *
     * @param arr
     * @param target
     * @return
     */
    private int[] solution(int[] arr, int target) {
        int[] res = new int[2];
        if (arr == null || arr.length < 2) return res;
        int N = arr.length;
        // 定义好左右指针
        int left = 0, right = N - 1;
        while (left < right) {
            if (target == arr[left] + arr[right]) {
                res[0] = left + 1;
                res[1] = right + 1;
                return res;
            } else {
                if (target > arr[left] + arr[right]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    /**
     * 利用二分查找
     *
     * @param arr
     * @param target
     * @return
     */
    private int[] solution1(int[] arr, int target) {
        int[] res = new int[2];
        if (arr == null || arr.length < 2) return res;
        int N = arr.length;
        for (int first = 0; first < N; first++) {
            int result = binarySearch(arr, first + 1, N - 1, target - arr[first]);
            if (result != -1) {
                // 下标从1开始
                res[0] = first + 1;
                res[1] = result + 1;
                return res;
            }
        }
        return res;
    }

    /**
     * 二分查找
     *
     * @param arr
     * @param L
     * @param R
     * @param target
     * @return
     */
    private int binarySearch(int[] arr, int L, int R, int target) {
        if (arr == null || arr.length == 0) return -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == target) {
                return mid;
            } else {
                if (arr[mid] > target) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
        }
        return -1;
    }
}
