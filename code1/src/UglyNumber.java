/*一个数的因子仅仅包括2，3，5的数称为丑数。 数字1特别对待也看作是丑数，
 所以从1開始的10个丑数分别为1、2、3、4、5、6、8、9、 10、12
 返回第n个丑数
*/

/**
 * @author huangcheng
 */
public class UglyNumber {
  /**
   * @param n 数字
   * @return 返回第n个丑数
   */
  public static int uglyNumber(int n) {
    int[] help = new int[n];
    help[0] = 1;
    int i2 = 0;
    int i3 = 0;
    int i5 = 0;
    int index = 1;
    while (index < n) {
      help[index] = Math.min(2 * help[i2], Math.min(3 * help[i3], 5 * help[i5]));
      if (help[index] == 2 * help[i2]) {
        i2++;
      }
      if (help[index] == 3 * help[i3]) {
        i3++;
      }
      if (help[index] == 5 * help[i5]) {
        i5++;
      }
      index++;
    }
    return help[index - 1];

  }

  public static void main(String[] args) {
    //运行示意过程
    //(2*1,(3*1,5*1))=>i2 2
    //(2*2,(3*1,5*1))=>i3 3
    //(2*2,(3*2,5*1))=>i2 4
    //(2*3,(3*2,5*1))=>i5 5
    //(2*3,(3*2,5*2))=>i2,i3 6
    //(2*4,(3*3,5*2))=>i2 8
    //(2*5,(3*3,5*2))=>i3 9
    //(2*5,(3*4,5*2))=>i2,i5 10
    //(2*6,(3*4,5*3))=>i2,i3 12
    //(2*7,(3*5,5*3))=>i3,i5 15
    System.out.println(uglyNumber(7));
  }
}
