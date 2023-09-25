import java.util.Scanner;

public class CountThree {
  /**
     * 시간안에 3이 포함되어 있는지 확인하는 문제
     */
  public static boolean check(int h, int m, int s) {
      if (h % 10 == 3 || m / 10 == 3 || m % 10 == 3 || s / 10 == 3 || s % 10 == 3) {
        return true;
      }
      return false;
    }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    
    int h = sc.nextInt();
    int count = 0;
    for (int i = 0; i <= h; i++) {
      for (int j = 0; j < 60; j++) {
        for (int k = 0; k < 60; k++) {
          if (check(i, j, k)) {
            count++;
          }
        }
      }
    }

    System.out.println(count);
    sc.close();
  }
}