/**
 * 给定一个无序数组arr，如果只能对一个子数组进行排序，但是想让数组整体都有序，求需要排序的最短子数组长度。
 * 例如:arr = [1,5,3,4,2,6,7]返回4，因为只有[5,3,4,2]需要排序
 *
 * @author huangcheng
 */
public class MinLengthForSort {
  public static int getMinLength(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }
    int min = arr[arr.length - 1];
    int noMinIndex = -1;

    for (int i = arr.length - 2; i != -1; i--) {
      //从右往左扫描，找到比最后一个右扫描最小值大的数的位置
      if (arr[i] > min) {
        noMinIndex = i;
      } else {
        // 当前位置的数和右扫描最小值比较，小的数赋值给min
        min = Math.min(min, arr[i]);
      }
    }
    if (noMinIndex == -1) {
      return 0;
    }
    int max = arr[0];
    int noMaxIndex = -1;
    for (int i = 1; i != arr.length; i++) {
      //从左往右扫描，找到最后一个比左扫描最大值小的数的位置
      if (arr[i] < max) {
        noMaxIndex = i;
      } else {
        // 当前位置的数和左扫描最大值比较，大的数赋值给max
        max = Math.max(max, arr[i]);
      }
    }
    return noMaxIndex - noMinIndex + 1;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
    int[] arr2 = {1, 5, 3, 4, 2, 6, 7};
    System.out.println(getMinLength(arr));
    System.out.println(getMinLength(arr2));


  }
}
