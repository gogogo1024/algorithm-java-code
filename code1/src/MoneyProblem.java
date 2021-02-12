/**
 * @author huangcheng
 */
public class MoneyProblem {
  /**
   * @param d     i号怪兽的能力
   * @param p     i号怪兽要求的钱
   * @param hp    当前你具有的能力值
   * @param index 来到了第index个怪兽的面前
   * @return 当前能力是hp，到达了第index号怪兽的面前
   * 如果需要通过后续所有的怪兽，需要花费最少的钱是多少
   * 当前适合怪兽武力值范围分布小，钱数范围分布大
   */
  public static long process(int[] d, int[] p, int hp, int index) {
    if (index == d.length) {
      return 0;
    }
    // 当前能力小于第i号怪兽的能力，花钱购买能力
    if (hp < d[index]) {
      return p[index] + process(d, p, hp + d[index], index + 1);
    } else {
      // 1. 可以不花钱通过，因为此时能力值大于i号当前怪兽的能力,不增加能力值
      // 2. 也可以花钱通过增加能力值
      // 比较两种情况花钱最少的
      return Math.min(
        // 花钱购买第i号怪兽的能力
        // 第i号怪兽要求的钱+后续关卡需要的钱
        p[index] + process(d, p, hp + d[index], index + 1),
        // 应为不花钱 后续关卡需要的钱
        process(d, p, hp, index + 1)
      );
    }
  }

  /**
   * 动态规划（钱数小，能力值大）
   * 行是怪兽能力数组，列是固定钱数，dp[i][j]含义是通过从0号怪兽
   * 到第i怪兽通过时，需要花费固定钱数j,能够到达的最大能力
   *
   * @param d
   * @param p
   * @return
   */
  public static long process2(int[] d, int[] p) {
    int sum = 0;
    for (int num : p) {
      sum += num;
    }
    //dp[i][j]含义
    //能经过0~i的怪兽，且花钱为j（严格等于j）时的武力值最大是什么？
    // 如果dp[i][j] == - 1,表示经过0～i的怪兽，花钱为j是无法通过的，
    // 或者之前的钱怎么组合也不能严格等于j
    int[][] dp = new int[d.length][sum + 1];
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j <= sum; j++) {
        dp[i][j] = -1;
      }
    }
    // 经过0～i的怪兽，花钱数一定为p[0],达到武力值d[0]的地步，
    // 其他第0行的状态一定是无效的（-1)
    dp[0][p[0]] = d[0];
    for (int i = 1; i < d.length; i++) {
      for (int j = 0; j <= sum; j++) {
        // 为当前怪兽花钱
        if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
          dp[i][j] = dp[i - 1][j - p[i]] + d[i];
        }
        // 不为当前怪兽花钱（不花钱就不能把当前位置的怪兽武力值加给自己）
        if (dp[i - 1][j] >= d[i]) {
          // 选武力值大的
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
        }
      }
    }
    int ans = 0;
    // dp表最后一行，dp[N-1][j]代表
    // 能经过过0～N-1的怪兽，且花钱为j时的武力值最大是多少？
    // 最后一行上，最左侧的不为-1的列j，就是答案
    for (int j = 0; j <= sum; j++) {
      if (dp[d.length - 1][j] != -1) {
        ans = j;
        break;
      }
    }
    return ans;
  }

  public static int[][] generateTwoRandomArray(int len, int value) {
    int size = (int) (Math.random() * len) + 1;
    int[][] arrs = new int[2][size];
    for (int i = 0; i < size; i++) {
      arrs[0][i] = (int) (Math.random() * value) + 1;
      arrs[1][i] = (int) (Math.random() * value) + 1;
    }
    return arrs;
  }

  public static void main(String[] args) {
    int len = 10;
    int value = 20;
//    int testTimes = 1000000;
//    for (int i = 0; i < testTimes; i++) {
    int[][] arrs = generateTwoRandomArray(len, value);
    int[] d = arrs[0];
    int[] p = arrs[1];
    long ans1 = process2(d, p);
    System.out.println(ans1);
//    }
  }
}
