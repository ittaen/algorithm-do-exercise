package binary_search;

import sort.base.RandomArrayGenerator;

/**
 * 二分查找-I
 * <p>
 * 请实现无重复数字的升序数组的二分查找
 * 给定一个 元素升序的、无重复数字的整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标（下标从 0 开始），否则返回 -1
 * 数据范围： len(nums) 0≤len(nums)≤10^5
 * ， 数组中任意值满足 ∣val∣≤10^9
 * 进阶：时间复杂度 O(logn) ，空间复杂度 O(1)
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/20 9:25
 */
public class Search_I {
    public static void main(String[] args) {
        Search_I app = new Search_I();
        int maxSize = 20;
        int maxValue = 20;
        int[] arr = RandomArrayGenerator.generateRandomArray3(maxSize, maxValue);
        int target = RandomArrayGenerator.getRandomNumInArray(maxValue, 0.8, arr, 0.5);
        RandomArrayGenerator.printSearchInArray(arr, target, "输入：");
        System.out.println("输出： " + app.search(arr, target));
    }

    public int search(int[] nums, int target) {
        return solution(nums, target);
    }

    private int solution(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        int L = 0;
        int R = N -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return -1;
    }


}
