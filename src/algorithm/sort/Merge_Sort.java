package algorithm.sort;

import algorithm.sort.base.RandomArrayGenerator;

/**
 * 归并排序
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/19 16:26
 */
public class Merge_Sort {
    public static void main(String[] args) {
        int testTime = 50_0000;
        //int testTime = 1000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = RandomArrayGenerator.generateRandomArray(maxSize, maxValue);
            int[] arr2 = RandomArrayGenerator.copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!RandomArrayGenerator.isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                RandomArrayGenerator.printArray(arr1);
                RandomArrayGenerator.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 递归方式 归并排序
     *
     * @param arr
     */
    private static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        divideAndMerge(arr, 0, arr.length - 1);
    }

    private static void divideAndMerge(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        // 中间位置 为什么这样写？防止加法越界溢出
        int mid = L + ((R - L) >> 1);
        divideAndMerge(arr, L, mid);
        divideAndMerge(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        // 为合并后的数组申请空间
        int[] merged = new int[R - L + 1];
        int mergeIndex = 0;
        int p1 = L;
        int p2 = M + 1;
        // 当 p1 和 p2 都没有越界
        while (p1 <= M && p2 <= R) {
            merged[mergeIndex++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 当 p1 没有越界
        while (p1 <= M) {
            merged[mergeIndex++] = arr[p1++];
        }
        // 当 p2 没有越界
        while (p2 <= R) {
            merged[mergeIndex++] = arr[p2++];
        }
        // 拷贝回原数组
        //if (merged.length >= 0) System.arraycopy(merged, 0, arr, L, merged.length);
        for (int i = 0; i < merged.length; i++) {
            arr[L + i] = merged[i];
        }
    }

    /**
     * 非递归方式 归并排序
     *
     * @param arr
     */
    private static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                // 求中点 且考虑 L + mergeSize 不能超过 N
                int M;
                if (N - L >= mergeSize) {
                    M = L + mergeSize - 1;
                } else {
                    break;
                }
                // 求右边界
                int R;
                if (N - 1 - M >= mergeSize) {
                    R = M + mergeSize;
                } else {
                    R = N - 1;
                }
                merge(arr, L, M, R);
                if (R == N - 1) {
                    // 此时右边已经没有了
                    break;
                } else {
                    L = R + 1;
                }
            }
            // 保证下一步的 mergeSize << 1 不会溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    /**
     * 非递归方式 归并排序
     * （优化）
     *
     * @param arr
     */
    private static void mergeSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                // 不够 mergeSize （L + mergeSize 不能超过 N）
                if (mergeSize >= N - L) {
                    break;
                }
                // 求中点
                int M = L + mergeSize - 1;
                // 求右边界 考虑 mergeSize 和 N - M - 1 的大小，取小值
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1; // 循环内是控制 L < N 的
            }
            // 保证下一步的 mergeSize << 1 不会溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

}
