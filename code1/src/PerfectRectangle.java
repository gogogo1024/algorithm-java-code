import java.util.HashSet;

/**
 * @author huangcheng
 */
public class PerfectRectangle {
  public boolean isRectangleCover(int[][] rectangles) {
    if (rectangles.length == 0 || rectangles[0].length == 0) {
      return false;
    }
    int xL = Integer.MAX_VALUE;
    int xR = Integer.MIN_VALUE;
    int yD = Integer.MAX_VALUE;
    int yU = Integer.MIN_VALUE;
    HashSet<String> set = new HashSet<String>();
    int area = 0;
    for (int[] rec : rectangles) {
      // rect[0] 左下角点x坐标
      // rect[1] 左下角点y坐标
      // rect[2] 右上角点x坐标
      // rect[3] 右上角点y坐标
      xL = Math.min(rec[0], xL);
      xR = Math.max(rec[2], xR);
      yD = Math.max(rec[1], yD);
      yU = Math.max(rec[3], yU);
      area += (rec[2] - rec[0]) * (rec[3] - rec[1]);
      // 左下角点坐标
      String s1 = rec[0] + "_" + rec[1];
      // 左上角点坐标
      String s2 = rec[0] + "_" + rec[3];
      // 右上角点坐标
      String s3 = rec[2] + "_" + rec[3];
      // 右下角点坐标
      String s4 = rec[2] + "_" + rec[1];
      // 出现偶数次，set移入后依然会删除该s
      if (!set.add(s1)) {
        set.remove(s1);
      }
      if (!set.add(s2)) {
        set.remove(s2);
      }

      if (!set.add(s3)) {
        set.remove(s3);
      }
      if (!set.add(s4)) {
        set.remove(s4);
      }
    }
    // 除了四个顶角所在点出现一次，其他的点出现次数是偶数
    // 经过上面的处理，就剩下了四个顶点了
    if (!set.contains(xL + "_" + yD) || !set.contains(xL + "_" + yU) ||
      !set.contains(xR + "_" + yD) || !set.contains(xR + "_" + yU) || set.size() != 4
    ) {
      return false;
    }
    // 面积之和相等
    return area == (xR - xL) * (yU - yD);
  }
}
