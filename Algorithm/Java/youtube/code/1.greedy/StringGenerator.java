import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StringGenerator {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String data = sc.nextLine();

    ArrayList<Character> result = new ArrayList<>();
    int value = 0;

    for(int i = 0; i < data.length(); i++) {
      // 영문을 구별하는 정적메서드가 존재함
      if (Character.isLetter(data.charAt(i))){
        result.add(data.charAt(i));
      } else {
        // 문자를 숫자로 변경할때 사용한다.
        value += data.charAt(i) - '0';
      }
    }

    // 알파벳은 소트가 된다. (아스키로 안바꿔도 된다.)
    Collections.sort(result);

    // List에 담긴 Character를 print를 통해 하나씩 찍어낸다.
    for (int j = 0; j < result.size(); j++) {
      System.out.print(result.get(j));
    }

    if (value != 0) {
      System.out.print(value);
    }
    sc.close();
  }
}
