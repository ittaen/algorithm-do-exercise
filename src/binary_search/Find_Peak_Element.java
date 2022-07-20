package binary_search;

import sort.base.RandomArrayGenerator;

/**
 * 寻找峰值
 * <p>
 * 给定一个长度为n的数组arr，请你找到峰值并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
 * 1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
 * 2.假设 arr[-1] = arr[n] = −∞
 * 3.对于所有有效的 i 都有 arr[i] != arr[i + 1]
 * 4.你可以使用O(logN)的时间复杂度实现此问题吗？
 * 数据范围：
 * 1≤arr.length≤2×10^5
 * -2^31<= arr[i] <= 2^31
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/20 15:10
 */
public class Find_Peak_Element {
    public static void main(String[] args) {
        Find_Peak_Element app = new Find_Peak_Element();
        int[] arr = RandomArrayGenerator.generatorRandomArray2(10, 20);
        int peakElement = app.findPeakElement(arr);
        RandomArrayGenerator.printArray(arr);
        System.out.println(peakElement);
    }

    public int findPeakElement(int[] arr) {
        return solution(arr);
    }

    /**
     * 二分查找 (优化)
     *
     * @param arr
     * @return
     */
    private int solution(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            // 从左往右向下
            if (arr[mid] > arr[mid + 1]) {
                R = mid;
            } else {
                // 从左往右向上
                L = mid + 1;
            }
        }

        return R;
    }

    /**
     * 二分查找
     *
     * @param arr
     * @return
     */
    private int solution1(int[] arr) {
        if (arr == null || arr.length == 0) return -1;

        int N = arr.length;
        if (N == 1) return 0;

        if (arr[0] > arr[1]) return 0;
        if (arr[N - 1] > arr[N - 2]) return N - 1;

        int L = 0;
        int R = N - 1;

        while (L < R - 1) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else {
                if (arr[mid] < arr[mid - 1]) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
        }

        return arr[L] > arr[R] ? L : R;
    }

}
