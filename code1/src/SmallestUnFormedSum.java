/**
 * @author huangcheng
 */

import java.util.Arrays;

/**
 * 给定一个正数数组 arr，其中所有的值都为整数，以下是最小不可组成和的概念:
 * 把 arr 每个子集内的所有元素加起来会出现很多值，其中最小的记为 min，最大的记为max 在区间[min,max]上，
 * 如果有数不可以被arr某一个子集相加得到，那么其中最小的那个数是arr 的最小不可组成和 在区间[min,max]上，
 * 如果所有的数都可以被arr的某一个子集相加得到，那么max+1是arr的最小不可组成和
 * 请写函数返回正数数组 arr 的最小不可组成和。
 * 【举例】
 * arr=[3,2,5]。子集{2}相加产生 2 为 min，子集{3,2,5}相加产生 10 为 max。
 * 在区间[2,10] 上，4、 6 和 9 不能被任何子集相加得到，其中 4 是 arr的最小不可组成和。 arr=[1,2,4]。
 * 子集{1}相加产生 1 为 min，子集{1,2,4}相加产生 7 为 max。在区间[1,7]上， 任何数都可以被子集相加得到，
 * 所以 8 是arr的最小不可组成和。
 * 【进阶】
 * 如果已知正数数组 arr 中肯定有 1 这个数，是否能更快地得到最小不可组成和?
 * 【举例】
 * arr = {1,2,3,7}，range = 15
 * 想累加得到 1~15 范围上所有的数，arr 还缺 14 这个数，所以返回1 arr = {1,5,7}，range = 15
 * 想累加得到 1~15 范围上所有的数，arr 还缺 2 和 4，所以返回2
 *
 * @author huangcheng
 */

public class SmallestUnFormedSum {
  public static int unformedSum(int[] arr) {
    int min = Integer.MAX_VALUE;
    int sum = 0;
    int N = arr.length;
    for (int i = 0; i < N; i++) {
      min = Math.min(min, arr[i]);
      sum += arr[i];
    }

    // dp[i][j]含义是 ar[0],ar[1]...ar[i]能不能组合出来j
    // 分两种情况 1.不使用i位置的数字也就是看dp[i-1][j]
    // 2. 使用i位置的数字也就是看dp[i-1][j-ar[i]]
    // 因此最后一行就是ar[0],ar[1]... ar[i]能够组合出来的所有范围是 min到max
    boolean[][] dp = new boolean[N][sum + 1];
    // 第一行只有 arr[0]位置是true
    dp[0][arr[0]] = true;
    for (int i = 1; i < N; i++) {
      for (int j = 1; j <= sum; j++) {
        if (arr[i] == j) {
          dp[i][j] = true;
        } else if (dp[i - 1][j]) {
          dp[i][j] = true;
        } else if (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]) {
          //保持索引不越界
          dp[i][j] = true;
        }
      }
    }
    int ans = min;
    // 最后一行最小值
    for (; ans <= sum; ans++) {
      if (!dp[N - 1][ans]) {
        return ans;
      }
    }
    return sum + 1;
  }

  public static int advancedUnformedSum(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    Arrays.sort(arr);
    int N = arr.length;
    int range = 1;
    // 对于最小数是1的情况是加1处理，对于最小数是N的情况是加N处理，
    for (int i = 1; i < N; i++) {
      if (arr[i] > range + 1) {
        return range + 1;
      } else {
        range += arr[i];
      }
    }
    return range + 1;
  }


  public static void main(String[] args) {
    int[] arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
    System.out.println(unformedSum(arr));
    int[] arr2 = {1, 2, 3, 7};

    System.out.println(advancedUnformedSum(arr2));
  }

}
