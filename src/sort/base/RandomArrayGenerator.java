package sort.base;

/**
 * 随机数组生成器
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/14 16:15
 */
public class RandomArrayGenerator {
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    /**
     * 生成随机数组，相邻数不相等
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generatorRandomArray2(int maxSize, int maxValue) {
        int len = (int) ((maxSize + 1) * Math.random());
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) ((maxValue + 1) * Math.random());
            for (int i = 1; i < arr.length; i++) {
                do {
                    arr[i] = (int) ((maxValue + 1) * Math.random());
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
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

    public static void printArray(int[] arr, int value) {
        if (arr == null) {
            return;
        }
        System.out.print(value + " ---- ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
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
