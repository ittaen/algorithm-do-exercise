package binary_search;

/**
 * 比较版本号
 * <p>
 * 牛客项目发布项目版本时会有版本号，比如1.02.11，2.14.4等等
 * 现在给你2个版本号version1和version2，请你比较他们的大小
 * 版本号是由修订号组成，修订号与修订号之间由一个"."连接。
 * 1个修订号可能有多位数字组成，修订号可能包含前导0，且是合法的。
 * 例如，1.02.11，0.1，0.2都是合法的版本号
 * 每个版本号至少包含1个修订号。
 * 修订号从左到右编号，下标从0开始，最左边的修订号下标为0，
 * 下一个修订号下标为1，以此类推。
 * 比较规则：
 * 一. 比较版本号时，请按从左到右的顺序依次比较它们的修订号。
 * 比较修订号时，只需比较忽略任何前导零后的整数值。
 * 比如"0.1"和"0.01"的版本号是相等的
 * 二. 如果版本号没有指定某个下标处的修订号，则该修订号视为0。
 * 例如，"1.1"的版本号小于"1.1.1"。
 * 因为"1.1"的版本号相当于"1.1.0"，第3位修订号的下标为0，小于1
 * 三.  version1 > version2 返回1，如果 version1 < version2 返回-1，不然返回0.
 * 数据范围：
 * 1 <= version1.length, version2.length <= 10001<=version1.length,version2.length<=1000
 * version1 和 version2 的修订号不会超过int的表达范围，即不超过 32 位整数 的范围
 * 进阶：  时间复杂度 O(n)
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/20 17:56
 */
public class Compare_Version {
    public static void main(String[] args) {
        Compare_Version app = new Compare_Version();
        System.out.println(app.compare("1.1", "2.1"));
    }

    public int compare(String version1, String version2) {
        return solution(version1, version2);
    }

    private int solution(String version1, String version2) {
        String[] nums1 = version1.split(".");
        String[] nums2 = version2.split(".");
        for (int i = 0; i < nums1.length || i < nums2.length; i++) {
            // 版本号 较短的取0
            String str1 = i < nums1.length ? nums1[i] : "0";
            String str2 = i < nums2.length ? nums2[i] : "0";
            long num1 = 0;
            // 字符串转数字
            for (int j = 0; j < str1.length(); j++) {
                num1 = num1 * 10 + (str1.charAt(j) - '0');
            }
            long num2 = 0;
            // 字符串转数字
            for (int j = 0; j < str2.length(); j++) {
                num2 = num2 * 10 + (str2.charAt(j) - '0');
            }
            // 比较数字大小
            if (num1 > num2) return 1;
            if (num1 < num2) return -1;
        }
        // 版本相同
        return 0;
    }

    private int solution1(String version1, String version2) {
        int len1 = version1.length();
        int len2 = version2.length();
        int i = 0, j = 0;
        while (i < len1 || j < len2) {
            long num1 = 0;
            // 取数字到下一个点之前
            while (i < len1 && version1.charAt(i) != '.') {
                num1 = num1 * 10 + (version1.charAt(i) - '0');
                i++;
            }
            // 跳过点
            i++;
            long num2 = 0;
            // 取数字到下一个点之前
            while (j < len2 && version2.charAt(j) != '.') {
                num2 = num2 * 10 + (version2.charAt(j) - '0');
                j++;
            }
            // 跳过点
            j++;
            // 比较数字大小
            if (num1 > num2) return 1;
            if (num1 < num2) return -1;
        }
        return 0;
    }
}
