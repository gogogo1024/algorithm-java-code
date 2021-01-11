import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定两个有序数组arr1和arr2，再给定一个整数k，返回来自arr1和arr2的两个数相加和最大的前k个，
 * 两个数必须分别来自两个数组。
 * 【举例】
 * arr1=[1,2,3,4,5]，arr2=[3,5,7,9,11]，k=4。 返回数组[16,15,14,14]
 * 【要求】
 * 时间复杂度达到 O(klogk)
 *
 * @author huangcheng
 */
public class TopKSumCrossTwoArrays {
  public static class Node {
    public int index1;
    public int index2;
    public int sum;

    public Node(int i1, int i2, int s) {
      index1 = i1;
      index2 = i2;
      sum = s;
    }
  }

  public static class MaxHeapComp implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
      return o2.sum - o1.sum;
    }

    public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
      if (arr1 == null || arr2 == null || topK < 1) {
        return null;
      }
      topK = Math.min(topK, arr1.length * arr2.length);
      int[] res = new int[topK];
      int resIndex = 0;
      // 大跟堆 MaxHeapComp 利用sum来做优先级的比较
      //  从矩阵的最右下角开始处理，方向是向左或者向上
      // 小跟堆 从矩阵的最左上开始处理，方向是向右或者向下
      PriorityQueue<Node> maxHeap = new PriorityQueue<>(new MaxHeapComp());
      // 存储加入的位置
      boolean[][] set = new boolean[arr1.length][arr1.length];
      int i1 = arr1.length - 1;
      int i2 = arr2.length - 1;
      // 先加上最后一个节点也即是最大的节点
      maxHeap.add(new Node(i1, i2, arr1[i1] + arr2[i2]));
      set[i1][i2] = true;
      while (resIndex != topK) {
        // 利用
        Node curNode = maxHeap.poll();
        res[resIndex++] = curNode.sum;
        i1 = curNode.index1;
        i2 = curNode.index2;
        if (i1 - 1 >= 0 & !set[i1 - 1][i2]) {
          set[i1 - 1][i2] = true;
          maxHeap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
        }
        if (i2 - 1 >= 0 & !set[i1][i2 - 1]) {
          set[i1][i2 - 1] = true;
          maxHeap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
        }
      }
      return res;
    }
  }

//  public static int[] topKSumTest(int[] arr1, int[] arr2, int topK) {
//    int[] all = new int[arr1.length * arr2.length];
//    int index = 0;
//    for (int i = 0; i != arr1.length; i++) {
//      for (int j = 0; j != arr2.length; j++) {
//        all[index++] = arr1[i] + arr2[j];
//      }
//    }
//    return all;
//  }
}
