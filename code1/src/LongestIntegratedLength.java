/**
 * 先给出可整合数组的定义:如果一个数组在排序之后，每相邻两个数差的绝对值 都为 1， 则该数组为可整合数组。
 * 例如，[5,3,4,6,2]排序之后为[2,3,4,5,6]，
 * 符合每相邻两个数差的绝对值 都为 1，所以这个数组为可整合数组。
 * 给定一个整型数组 arr，请返回其中最大可整合子数组的长度。例如，
 * [5,5,3,2,6,4,3]的最大 可整合子数组为[5,3,2,6,4]，所以返回 5。
 */

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author huangcheng
 */
public class LongestIntegratedLength {

  /**
   * 暴力解法
   *
   * @param arr 数组
   * @return 可组合数组的最大长度
   */
  public static int getLIL1(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int len = 0;
    // 以i开头，枚举j结尾
    // O(N*N*N*logN)
    for (int i = 0; i < arr.length; i++) {
      for (int j = i; j < arr.length; j++) {
        if (isIntegrated(arr, i, j)) {
          len = Math.max(len, j - 1 + 1);
        }
      }
    }
    return len;
  }

  /**
   * @param arr   数组
   * @param left  数组左边界
   * @param right 数组右边界
   * @return 是否是可整合
   */
  public static boolean isIntegrated(int[] arr, int left, int right) {
    int[] newArr = Arrays.copyOfRange(arr, left, right);
    // O(N*logN)
    Arrays.sort(newArr);
    for (int i = 0; i < newArr.length; i++) {
      if (newArr[i - 1] != newArr[i - 1]) {
        return false;
      }
    }
    return true;
  }

  /**
   * 简介解法
   * 1.无重复值
   * 2. max-min = 个数-1
   *
   * @param arr 数组
   * @return 可组合数组的最大长度
   */
  public static int getLIL2(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int ans = 0;
    int min = 0;
    int max = 0;
    // 用来判定重复
    HashSet<Integer> set = new HashSet<Integer>();
    for (int L = 0; L < arr.length; L++) {
      set.clear();
      max = Integer.MIN_VALUE;
      min = Integer.MAX_VALUE;
      // 验证 arr[L ... R] 数组
      // 以L位置开头子数组，结尾在R位置
      for (int R = L; R < arr.length; R++) {
        // arr[L..R] 出现重复值了，arr[L...R往后]就不需要去验证了
        // 一定是不可以整合的
        if (set.contains(arr[R])) {
          break;
        }
        // arr[L..R] 无重复值
        set.add(arr[R]);
        max = Math.max(max, arr[R]);
        min = Math.min(min, arr[R]);
        //arr[L...R]是可以整合
        if (max - min == R - L) {
          ans = Math.max(ans, R - L + 1);
        }
      }

    }
    return ans;
  }

  public static void main(String[] args) {
    int[] arr1 = {5, 5, 3, 2, 6, 4, 3};
    int[] arr2 = {5, 3, 4, 6, 2};

    System.out.println(getLIL2(arr1));
    System.out.println(getLIL2(arr2));

  }
}
