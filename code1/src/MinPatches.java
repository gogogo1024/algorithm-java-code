/**
 * @author huangcheng
 */
public class MinPatches {

  private static int minPatches(int[] arr, int range) {
    // 缺多少个数
    int patches = 0;
    // 可以组合出来1～touch连续数字
    long touch = 0;
    for (int i = 0; i != arr.length; i++) {
      //1～touch
      //arr[i]时，保证1～arr[i]-1的数字都是全的
      while (arr[i] > touch + 1) {
        // touch+1就是缺的数字
        touch = 2 * touch + 1;
        patches++;
        if (touch >= range) {
          return patches;
        }
      }
      touch += arr[i];
      if (touch >= range) {
        return patches;
      }
    }
    while (range >= touch + 1) {
      touch = 2 * touch + 1;
      patches++;
    }
    return patches;
  }

  public static void main(String[] args) {
    int[] test = {3, 17, 21, 78};
    int n = 67;
    System.out.println(minPatches(test, n));

  }
}
