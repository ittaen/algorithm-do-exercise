package binary_search;

import sort.base.RandomArrayGenerator;

/**
 * 数组中的逆序对
 * <p>
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P mod 1000000007
 * 数据范围：  对于 50% 的数据, size≤10^4
 * 对于 100% 的数据, size≤10^5
 * 数组中所有数字的值满足 0≤val≤1000000
 * 要求：空间复杂度 O(n)，时间复杂度 O(nlogn)
 * 输入描述：
 * 题目保证输入的数组中没有的相同的数字
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/20 15:51
 */
public class Inverse_Pairs {
    public static void main(String[] args) {
        Inverse_Pairs app = new Inverse_Pairs();
        int[] arr = RandomArrayGenerator.generateArray("[364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575]");
        RandomArrayGenerator.printArray(arr);
        System.out.println(app.InversePairs(arr));
        RandomArrayGenerator.printArray(arr);
    }

    public int InversePairs(int[] array) {
        return solution(array);
    }

    private int solution(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return divideAndMerge(arr, 0, arr.length - 1);
    }

    private int divideAndMerge(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        int lSize = divideAndMerge(arr, L, M);
        int rSize = divideAndMerge(arr, M + 1, R);
        int mergeSize = merge(arr, L, M, R);
        return (lSize + rSize + mergeSize) % 1000000007;
    }

    private int merge(int[] arr, int L, int M, int R) {
        int count = 0;
        int[] mergeArr = new int[R - L + 1];
        int mergeIndex = 0, p1 = L, p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            if (arr[p1] > arr[p2]) {
                // 右边数小，计算逆序对
                count += (M + 1 - p1);
                count %= 1000000007;
                mergeArr[mergeIndex++] = arr[p2++];
            } else {
                // 左边数小
                mergeArr[mergeIndex++] = arr[p1++];
            }
        }
        while (p1 <= M) {
            // 右边的数合并完了，左边还有数
            mergeArr[mergeIndex++] = arr[p1++];
        }
        while (p2 <= R) {
            // 左边的数完了，右边的数还有
            mergeArr[mergeIndex++] = arr[p2++];
        }
        // 拷贝回去
        if (mergeArr.length >= 0) System.arraycopy(mergeArr, 0, arr, L, mergeArr.length);
        return count;
    }

}
