import java.util.Scanner;

public class MultiplyOrPlus {

    /**
     * 곱하기 혹은 더하기

        각 자리 숫자(0 ~ 9)로만 이루어진 문자열 S가 주어졌을때 
        왼쩍부터 오른쪽으로 하나씩 모든 숫자를 학인하며 숫자 사이에 x 혹은 + 연산자를 넣어
        결과적으로 만들어진 가장 큰 수를 구하는 프로그램을 작성한다.
     */
    
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
