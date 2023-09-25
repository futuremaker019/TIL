import java.util.Scanner;

public class Knight {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String inputData = sc.nextLine();
    int row = inputData.charAt(1) - '0';
    int column = inputData.charAt(0) - 'a' + 1;

    // System.out.println(row);
    // System.out.println(column);

    int[] dirY = {2, 2, -2, -2, -1, 1, -1, 1};
    int[] dirX = {-1, 1, -1, 1, 2, 2, -2, -2};

    int count = 0;
    for (int i = 0; i < 8; i++) {
      int newRow = row + dirX[i];
      int newColumn = column + dirY[i];

      if (newRow >= 1 && newRow <= 8 && newColumn >= 1 && newColumn <=8) {
        count++;
      }
    }

    System.out.println(count);

  }

}
