package binary_search;

import sort.base.RandomArrayGenerator;

/**
 * 二维数组中的查找
 * <p>
 * 在一个二维数组array中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 * 给定 target = 3，返回 false。
 * 数据范围：矩阵的长宽满足 0≤n,m≤500 ， 矩阵中的值满足 0≤val≤10^9
 * 进阶：空间复杂度 O(1) ，时间复杂度 O(n+m)
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/20 10:43
 */
public class Find_In_Two_Dimensional_Array {
    public static void main(String[] args) {
        Find_In_Two_Dimensional_Array app = new Find_In_Two_Dimensional_Array();
        int testTime = 100;
        /*for (int i = 0; i < testTime; i++) {
            int maxSize = 100;
            int maxValue = 100;
            int[][] arr = RandomArrayGenerator.generateTDArray(maxSize, maxValue);
            int target = RandomArrayGenerator.getRandomNumInTDArray(maxValue, 1.0, arr, 0.5);
            RandomArrayGenerator.printSearchInTDArray(arr, target, "输入：");
            System.out.println("输出： " + app.Find(target, arr));
        }*/
        int[][] arr = RandomArrayGenerator.generateTDArray("[[1,2,8,9],[2,4,9,12],[4,7,10,13],[6,8,11,15]]");
        int target = 7;
        RandomArrayGenerator.printSearchInTDArray(arr, target, "输入：");
        System.out.println("输出： " + app.Find(target, arr));
    }

    public boolean Find(int target, int[][] array) {
        return solution(target, array);
    }

    /**
     * 双二分查找法
     * 难点在于整理常式，还是需要着手画图便于理解
     * if (arr[xMid][yMid] > target) {
     * 	// 舍弃大的范围，取小的范围
     * 	if (doubleBinarySearch(arr, xStart, xEnd, yStart, yMid - 1, target)) return true;
     * 	if (doubleBinarySearch(arr, xStart, xMid - 1, yMid, yEnd, target)) return true;
     * } else {
     * 	// 舍弃小的范围，取大的范围
     * 	if (doubleBinarySearch(arr, xStart, xEnd, yMid + 1, yEnd, target)) return true;
     * 	if (doubleBinarySearch(arr, xMid + 1, xEnd, yStart, yMid, target)) return true;
     * }
     *
     * @param target
     * @param arr
     * @return
     */
    private boolean solution(int target, int[][] arr) {
        if (arr == null || arr.length == 0) return false;
        return doubleBinarySearch(arr, 0, arr.length - 1, 0, arr[0].length - 1, target);
    }

    private boolean doubleBinarySearch(int[][] arr, int xStart, int xEnd, int yStart, int yEnd, int target) {
        if (xStart < 0 || yStart < 0) {
            return false;
        }
        if (xEnd >= arr.length || yEnd >= arr[0].length) {
            return false;
        }
        if (xStart > xEnd || yStart > yEnd) {
            return false;
        }
        int xMid = xStart + ((xEnd - xStart) >> 1);
        int yMid = yStart + ((yEnd - yStart) >> 1);
        if (arr[xMid][yMid] == target) {
            return true;
        }
        if (arr[xMid][yMid] > target) {
            // 舍弃大的范围，取小的范围
            if (doubleBinarySearch(arr, xStart, xEnd, yStart, yMid - 1, target)) return true;
            if (doubleBinarySearch(arr, xStart, xMid - 1, yMid, yEnd, target)) return true;
        } else {
            // 舍弃小的范围，取大的范围
            if (doubleBinarySearch(arr, xStart, xEnd, yMid + 1, yEnd, target)) return true;
            if (doubleBinarySearch(arr, xMid + 1, xEnd, yStart, yMid, target)) return true;
        }
        return false;
    }

    /**
     * 进行arr.length 次 二分查找
     *
     * @param target
     * @param arr
     * @return
     */
    private boolean solution1(int target, int[][] arr) {
        for (int[] outer : arr) {
            int index = binarySearch(outer, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    private int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int N = arr.length;
        int R = N - 1;
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
