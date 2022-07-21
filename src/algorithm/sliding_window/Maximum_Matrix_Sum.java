package algorithm.sliding_window;

import java.util.Scanner;

/**
 * 最大矩阵和
 * <p>
 * 标题：最大矩阵和 | 时间限制：1秒 | 内存限制：262144K | 语言限制：不限
 * 给定一个二维整数矩阵，要在这个矩阵中选出一个子矩阵，
 * 使得这个子矩阵内所有的数字和尽量大，
 * 我们把这个子矩阵称为和最大子矩阵，
 * 子矩阵的选取原则是原矩阵中一块相互连续的矩形区域。
 * 输入的第一行包含2个整数n, m(1 <= n, m <= 10)，
 * 表示一个n行m列的矩阵，下面有n行，每行有m个整数，
 * 同一行中，每2个数字之间有1个空格，最后一个数字后面没有空格，
 * 所有的数字的在[-1000, 1000]之间。
 * 输出描述:
 * 输出一行一个数字，表示选出的和最大子矩阵内所有的数字和。
 * 示例1
 * 输入
 * 3 4
 * -3 5 -1 5
 * 2 4 -2 4
 * -1 3 -1 3
 * 输出
 * 20
 * 说明
 * 一个3*4的矩阵中，后面3列的子矩阵求和加起来等于20，和最大。
 * <p>
 * https://www.peiluming.com/article/107
 * <p>
 * 解题思路：
 * 先把二维问题转化为一维问题，再联想到一维数组最大子数组问题
 * 复杂度O(N^3)
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/21 12:16
 */
public class Maximum_Matrix_Sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int max = matrix[0][0];
        // 行开始位置
        for (int rowStart = 0; rowStart < rows; rowStart++) {
            // 每一列的和,相当于rowStart..rowEnd的和 组成了一个一维数组最大和问题
            int[] colSum = new int[cols];
            // 行结束位置
            for (int rowEnd = rowStart; rowEnd < rows; rowEnd++) {
                int matrixSum = 0;
                // 计算元素和
                for (int i = 0; i < cols; i++) {
                    // 累加每一列的和
                    colSum[i] += matrix[rowEnd][i];
                    // 计算当前矩阵的最大和
                    matrixSum = Math.max(colSum[i], matrixSum + colSum[i]);
                    // 记录历史矩阵最大和值
                    max = Math.max(matrixSum, max);
                }
            }
        }

        // 输出最大矩阵和
        System.out.println(max);
    }

    /**
     * <p>
     * 面试题 17.24. 最大子矩阵
     * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
     * <p>
     * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
     * <p>
     * 注意：本题相对书上原题稍作改动
     * <p>
     * 示例：
     * 输入：
     * [
     * [-1,0],
     * [0,-1]
     * ]
     * 输出：[0,1,0,1]
     * 解释：输入中标粗的元素即为输出所表示的矩阵
     * 说明：
     * 1 <= matrix.length, matrix[0].length <= 200
     * <p>
     * https://leetcode.cn/problems/max-submatrix-lcci/
     * <p>
     *
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        return solution(matrix);
    }

    private int[] solution(int[][] matrix) {
        int[] res = new int[4];
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = matrix[0][0];
        // 行开始位置
        for (int rowStart = 0; rowStart < rows; rowStart++) {
            // 每一列的和,相当于rowStart..rowEnd的和 组成了一个一维数组最大和问题
            int[] colSum = new int[cols];
            // 行结束位置
            for (int rowEnd = rowStart; rowEnd < rows; rowEnd++) {
                int matrixSum = 0;
                // 计算元素和
                int colStart = 0;
                for (int i = 0; i < cols; i++) {
                    // 累加每一列的和
                    colSum[i] += matrix[rowEnd][i];
                    // 计算当前矩阵的最大和
                    matrixSum = Math.max(colSum[i], matrixSum + colSum[i]);
                    // colStart会更新,保留更新记录，但又不计入res，等到满足 matrixSum > max 才记录
                    if (colSum[i] == matrixSum) {
                        colStart = i;
                    }
                    // 记录历史矩阵最大和值
                    if (matrixSum > max) {
                        res[0] = rowStart;
                        res[1] = colStart;
                        res[2] = rowEnd;
                        res[3] = i;
                    }
                    max = Math.max(matrixSum, max);
                }
            }
        }
        return res;
    }

}
