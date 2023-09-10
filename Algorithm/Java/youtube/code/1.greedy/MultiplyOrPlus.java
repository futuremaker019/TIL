import java.util.Scanner;

public class MultiplyOrPlus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        /**
         * character룰 int로 변환하는 방법
         *
         * 1. ASC code에서 직접 값을 빼는 방법
         * 2. Character.getNumericValue() 메서드를 사용하여 변경
         */

        // 첫번째 자리의 값을 저장한다.
        int result = str.charAt(0) - '0';
        for (int i = 1; i < str.length(); i++) {
            int num = str.charAt(i) - '0';
            if (num <= 1 || result <= 1) {
                result += num;
            } else {
                result *= num;
            }
        }

        System.out.println("result = " + result);
    }
}
