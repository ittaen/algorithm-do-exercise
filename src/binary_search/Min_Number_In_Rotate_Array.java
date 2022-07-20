package binary_search;

import sort.base.RandomArrayGenerator;

/**
 * 旋转数组的最小数字
 * <p>
 * 有一个长度为 n 的非降序数组，比如[1,2,3,4,5]，
 * 将它进行旋转，即把一个数组最开始的若干个元素搬到数组的末尾，
 * 变成一个旋转数组，比如变成了[3,4,5,1,2]，或者[4,5,1,2,3]这样的。
 * 请问，给定这样一个旋转数组，求数组中的最小值。
 * 数据范围：1≤n≤10000，数组中任意元素的值: 0≤val≤10000
 * 要求：空间复杂度：O(1) ，时间复杂度：O(logn)
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/20 17:13
 */
public class Min_Number_In_Rotate_Array {
    public static void main(String[] args) {
        Min_Number_In_Rotate_Array app = new Min_Number_In_Rotate_Array();
        int[] arr = RandomArrayGenerator.generateArray("[2,2,2,1,2]");
        RandomArrayGenerator.printArray(arr);
        System.out.println(app.minNumberInRotateArray(arr));
    }

    public int minNumberInRotateArray(int[] array) {
        return solution(array);
    }

    /**
     * 二分法
     * 数组不是降序的 左边 < 右边
     * 那么，中点 < 右边，说明中点在左边数组
     * 如果，中点 > 右边，说明旋转点在右边，改变了趋势
     * @param arr
     * @return
     */
    private int solution(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int M = L + ((R - L) >> 1);
            if (arr[M] > arr[R]) {
                L = M + 1;
            } else if (arr[M] < arr[R]) {
                R = M;
            } else {
                R--;
            }
        }
        return arr[R];
    }
}
