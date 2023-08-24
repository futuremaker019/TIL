import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

public class DateFormatting {
    public static void main(String[] args) {

        /**
         * 날짜 조정, 파싱, 포메팅
         *  날짜와 관련된 모든 클래스는 불변이다.
         */
        LocalDate date = LocalDate.of(2017, 9, 21);
        date.withYear(2011);
        date.withDayOfMonth(25);
        LocalDate date3 = date.with(ChronoField.MONTH_OF_YEAR, 2);
        System.out.println("date3 = " + date3);

        date.plusWeeks(1);
        date.minusWeeks(2);
        date.plus(6, ChronoUnit.MONTHS);
    }
}
