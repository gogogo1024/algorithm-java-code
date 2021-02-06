/**
 * @author huangcheng
 */
public class MoneyProblem {
  /**
   * @param d     i号怪兽的能力
   * @param p     i号怪兽要求的钱
   * @param hp    当前你具有的能力值
   * @param index 来到了第index个怪兽的面前
   * @return 最少需要多少钱通过后续所有怪兽
   */
  public static long process(int[] d, int[] p, int hp, int index) {
    if (index == d.length) {
      return 0;
    }
    // 当前能力小于第i号怪兽的能力，花钱购买能力
    if (hp < d[index]) {
      return p[index] + process(d, p, hp + d[index], index + 1);
    } else {
      // 可以不花钱通过，因为此时能力值大于i号当前怪兽的能力,不增加能力值
      // 也可以花钱通过增加能力值
      // 比较不花钱
      return Math.min(
        // 花钱购买第i号怪兽的能力
        // 第i号怪兽要求的钱+后续关卡需要的钱
        p[index] + process(d, p, hp + d[index], index + 1),
        // 应为不花钱 后续关卡需要的钱
        process(d, p, hp, index + 1)
      );
    }
  }
}
