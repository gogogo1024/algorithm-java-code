public class StringCross {
  public static boolean isCross(String str1, String str2, String aim) {
    if (str1 == null || str2 == null || aim == null) {
      return false;
    }
    char[] ch1 = str1.toCharArray();
    char[] ch2 = str2.toCharArray();
    char[] chaim = aim.toCharArray();
    if (chaim.length != ch1.length + ch2.length) {
      return false;
    }
    // 初始化都是false
    // chaim 是 ch1前缀长度为i和ch2前缀长度为j的字符交错组合出来的
    // dp[i][j]为true表示ch1前缀长度为i的字符串，ch2前缀长度为j的字符串
    // 能不能交错组成一个chaim前缀长度为i+j的字符串
    boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];
    dp[0][0] = true;
    for (int i = 1; i <= ch1.length; i++) {
      if (ch1[i - 1] != chaim[i - 1]) {
        break;
      }
      dp[i][0] = true;
    }
    for (int j = 1; j <= ch2.length; j++) {
      if (ch2[j - 1] != chaim[j - 1]) {
        break;
      }
      dp[0][j] = true;
    }
    for (int i = 1; i <= ch1.length; i++) {
      for (int j = 1; j <= ch2.length; j++) {
        // 当chaim[i + j - 1]等于ch1的最后一个字符
        // 此时, ch1需要 0～i-2长度为i-1 和ch2需要0~j-1长度为j组合成chaim[i+j-2]
        // 也就是dp[i-1][j] 为true
        // 同理当chaim[i + j - 1]等于ch2的最后一个字符也一样
        if ((ch1[i - 1] == chaim[i + j - 1] && dp[i - 1][j])
          || (ch2[j - 1] == chaim[i + j - 1] && dp[i][j - 1])) {
          dp[i][j] = true;
        }
      }
    }
    return dp[ch1.length][ch2.length];
  }

  public static void main(String[] args) {
    String str1 = "1234";
    String str2 = "abcd";
    String aim = "1a23bcd4";
    System.out.println(isCross(str1, str2, aim));
  }
}
