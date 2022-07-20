package sort.base;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 随机数组生成器
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/14 16:15
 */
public class RandomArrayGenerator {
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[getRandomNum(maxSize)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getRandomNum(maxValue);
        }
        return arr;
    }

    /**
     * 生成随机数组，相邻数不相等
     *
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generatorRandomArray2(int maxSize, int maxValue) {
        int len = getRandomNum(maxSize);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = getRandomNum(maxValue);
            for (int i = 1; i < arr.length; i++) {
                do {
                    arr[i] = getRandomNum(maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    /**
     * 生成随机数组，无重复、升序
     *
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray3(int maxSize, int maxValue) {
        int len = getRandomNum(maxSize);
        int[] arr = new int[len];
        if (len > 0) {
            Set<Integer> set = new HashSet<>(len);
            arr[0] = getRandomNum(maxValue, 0.8);
            set.add(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                do {
                    arr[i] = getRandomNum(maxValue, 0.8);
                } while (set.contains(arr[i]));
            }
            Arrays.sort(arr);
        }
        return arr;
    }

    /**
     * 根据一个最大值生成随机数
     *
     * @param max
     * @return
     */
    public static int getRandomNum(int max) {
        return getRandomNum(max, 1.0);
    }

    /**
     * 根据一个最大值生成随机数
     * 负数的概率 20%
     *
     * @param max
     * @param ratio The probability of a positive number
     * @return
     */
    public static int getRandomNum(int max, double ratio) {
        if (Math.random() < ratio) {
            return (int) ((max + 1) * Math.random());
        } else {
            return -((int) ((max + 1) * Math.random()));
        }
    }

    /**
     * 根据一个最大值,以及一个数组 和 被数组包含的概率 生成随机数
     * 负数的概率 20%
     *
     * @param max
     * @param ratio The probability of a positive number
     * @return
     */
    public static int getRandomNumInArray(int max, double ratio, int[] arr, double containsRatio) {
        if (Math.random() < containsRatio && arr.length > 0) {
            Set<Integer> set = new HashSet<>();
            for (int value : arr) {
                set.add(value);
            }
            int num;
            do {
                num = getRandomNum(max, ratio);
            } while (!set.contains(num));
            return num;
        } else {
            return getRandomNum(max, ratio);
        }
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printSearchInArray(int[] arr, int target) {
        printSearchInArray(arr, target, "");
    }

    public static void printSearchInArray(int[] arr, int target, String prefix) {
        if (arr == null) {
            return;
        }
        if (prefix == null) {
            prefix = "";
        }
        boolean contains = false;
        StringBuilder sb = new StringBuilder();
        for (int value : arr) {
            if (value == target) {
                contains = true;
            }
            sb.append(value).append(" ");
        }
        sb.insert(0, String.format("%s%d (%s) ---- ", prefix, target, contains));
        System.out.println(sb.toString());
    }

    public static int testFindGeLeft(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) return i;
        }
        return -1;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return arr;
        }
        int[] replicate = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            replicate[i] = arr[i];
        }
        return replicate;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        int n = arr1.length;
        for (int i = 0; i < n; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 没有无符号左移
            // System.out.println((i >>> 1));
            System.out.println((1 << i));
        }
        System.out.println(Integer.MAX_VALUE + 1);
    }

}
