/**
 * 给出一组正整数，你从第一个数向最后一个数方向跳跃，每次至少跳跃1格，每个数的值表示你从这个位置可以跳跃的最大长度。
 * 计算如何以最少的跳跃次数跳到最后一个数。
 * 输入描述：
 * 第一行表示有多少个数n
 * 第二行开始依次是1到n个数，一个数一行输出描述：
 * 输出一行，表示最少跳跃的次数。输入
 * 7
 * 2
 * 3
 * 2
 * 1
 * 2
 * 1
 * 5
 * 输出3
 */
public class JumpGame {
  /**
   * @param arr 输入的数组
   * @return 到达最大边界最少需要多少步数
   * 利用当前步，收集下一步的最大边界
   */
  public static int jump(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    // 跳了多少步
    int jump = 0;
    // jump步内，右边界
    int cur = 0;
    // jump+1步内，右边界
    int next = 0;
    for (int i = 0; i < arr.length; i++) {
      // 当前cur边界小于i位置，因此步数必须加一，并且把下一步的最大边界next赋值给cur边界
      if (cur < i) {
        jump++;
        cur = next;
      }
      next = Math.max(next, i + arr[i]);
    }
    return jump;
  }

  public static void main(String[] args) {
    int[] arr = {2, 3, 2, 1, 2, 1, 5};
    System.out.println(jump(arr));
  }
}
