/**
 * 给定两个数组arrx和arry，长度都为N。代表二维平面上有N个点，第i个点的x 坐标和y坐标分别为arrx[i]和arry[i]，
 * 返回求一条直线最多能穿过多少个点？
 */

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOneLine {

  public static class Point {
    public int x;
    public int y;

    Point() {
      x = 0;
      y = 0;
    }

    Point(int a, int b) {
      x = a;
      y = b;
    }
  }


  /**
   * @param a a 被除数
   * @param b b 除数
   * @return 公约数
   */
  public static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  public static int maxPoints(Point[] points) {
    if (points == null) {
      return 0;
    }

    // 当只有一个点和两个点的情况
    if (points.length <= 2) {
      return points.length;
    }
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    int result = 0;
    for (int i = 0; i < points.length; i++) {
      map.clear();
      // 同位置
      int samPosition = 1;
      // 同横轴
      int sameX = 0;
      // 同纵轴
      int sameY = 0;
      int line = 0;
      for (int j = i + 1; j < points.length; j++) {
        int x = points[i].x - points[j].x;
        int y = points[i].y - points[j].y;
        if (x == 0 && points[j].y == 0) {
          samPosition++;
        } else if (x == 0) {
          sameX++;
        } else if (y == 0) {
          sameY++;
        } else {
          int gcd = gcd(x, y);
          x /= gcd;
          y /= gcd;
          // 3:{ {5:7},{7:10}}
          // 3对应x, 5对应y, 7和10对应line
          // 分子为3，分母为5的点有7个
          // 分子为3，分母为7的点有10个
          if (!map.containsKey(x)) {
            map.put(x, new HashMap<Integer, Integer>());
          }
          if (!map.get(x).containsKey(y)) {
            map.get(x).put(y, 0);
          }
          map.get(x).put(y, map.get(x).get(y) + 1);
          line = Math.max(line, map.get(x).get(y));
        }
      }
      result = Math.max(result, Math.max(Math.max(sameX, sameY), line)
        + samPosition);
    }
    return result;
  }

  public static void main(String[] args) {
    int[][] point = new int[][]{{1, 2}, {2, 4}, {6, 8}, {8, 10}, {1, 2}, {6, 7}};
    Point[] points = new Point[point.length];
    for (int i = 0; i < point.length; i++) {
      points[i] = new Point(point[i][0], point[i][1]);
    }
    System.out.println(maxPoints(points));
  }
}
