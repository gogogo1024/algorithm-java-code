//给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加 和相等，切分位置的数不要。
//  例如:
//  arr=[3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2] 返回true
//  三个切割点下标为2, 5, 7. 四个子数组为[3,2], [1,4], [5], [1,2,2]

import java.util.HashMap;

/**
 * @author huangcheng
 */
public class SplitParts {
  public static boolean canSplit(int[] arr) {
    if (arr == null || arr.length < 7) {
      return false;
    }
    HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      hashMap.put(sum, i);
    }
    int lsum = arr[0];
    for (int s1 = 1; s1 < arr.length - 5; s1++) {
      int checkSum = lsum * 2 + arr[s1];
      // 每一次的containsKey都是切割的地方，一共三次
      if (hashMap.containsKey(checkSum)) {
        int s2 = hashMap.get(checkSum);
        checkSum += lsum + arr[s2 + 1];
        if (hashMap.containsKey(checkSum)) {
          int s3 = hashMap.get(checkSum);
          checkSum += lsum + arr[s3 + 1];
          if (hashMap.containsKey(checkSum)) {
            if (checkSum == sum) {
              return true;
            }
          }
        }
      }
      lsum += arr[s1];
    }
    return false;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{3, 3, 9, 2, 4, 7, 6, 8, 1, 5};
    System.out.println(canSplit(arr));
  }
}
