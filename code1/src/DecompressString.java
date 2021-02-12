/**
 * 已知某个字符串只含有小写字母，压缩之后的字符串str包含数字、大括号和小写字符。
 * 请根据str还原出原始字符串并返回。
 * 比如:
 * 3{2{abc}} -> abcabcabcabcabcabc 3{a}2{bc} -> aaabcbc
 * 3{a2{c}} -> accaccacc
 */

/**
 * @author huangcheng
 */
public class DecompressString {
  public static String depcomress(String decompressString) {
    char[] chs = decompressString.toCharArray();
    return process(chs, 0).str;
  }

  /**
   * 转化结构
   */
  public static class ReturnData {
    public String str; // 遍历完这一段的转化结果
    public int end; // 转化到了什么位置

    public ReturnData(String str, int nextIndex) {
      this.str = str;
      this.end = nextIndex;
    }
  }

  // str[index...结尾或者遇到右括号]这一段，字符串，以及处理到到的位置返回
  public static ReturnData process(char[] str, int index) {
    StringBuilder res = new StringBuilder();
    int times = 0;
    while (index < str.length && str[index] != '}') {
      // 当遇到'{'交给下一个process处理
      // 同时重置times,记录最后的转换位置end
      if (str[index] == '{') {
        ReturnData returnData = process(str, index + 1);
        res.append(getTimesString(times, returnData.str));
        times = 0;
        index = returnData.end + 1;
      } else {
        // 数字
        if (str[index] >= '0' && str[index] <= '9') {
          times = times * 10 + str[index] - '0';
        }
        // 字符
        if (str[index] >= 'a' && str[index] <= 'z') {
          res.append(String.valueOf(str[index]));
        }
        index++;
      }
    }
    return new ReturnData(res.toString(), index);
  }

  private static String getTimesString(int times, String base) {
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < times; i++) {
      res.append(base);
    }
    return res.toString();
  }

  public static void main(String[] args) {
    String str = depcomress("aa2{cb7{k}}fc}");
    System.out.println(str);
  }
}
