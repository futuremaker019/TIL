import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Basic {
    public static void main(String[] args) {
        /** LocalDate, LocalTime, LocalDateTime*/

        /** LocalDate */
        LocalDate date = LocalDate.of(2017, 9, 21);
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);  //  THURSDAY
        int lengthOfMonth = date.lengthOfMonth();
        boolean leapYear = date.isLeapYear();

        LocalDate.now();

        // TemporalField 를 이용해 Localdate값 얻기
        date.get(ChronoField.YEAR);
        date.get(ChronoField.MONTH_OF_YEAR);
        date.get(ChronoField.DAY_OF_MONTH);

        /** LocalTime */
        LocalTime time = LocalTime.of(13, 45, 20);
        time.getHour();
        time.getMinute();
        time.getSecond();

        LocalDate date1 = LocalDate.parse("2017-09-21");
        LocalTime time1 = LocalTime.parse("13:45:20");

        /**
         * LocalDateTime
         *  2017-09-21T13:45:20 을 만드는 여러가지 방법
          */
        LocalDateTime localDateTime = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 45, 20);
        LocalDateTime localDateTime1 = LocalDateTime.of(date1, time1);
        date1.atTime(13, 45, 20);
        date1.atTime(time1);
        time1.atDate(date1);

        /**
         * Instant
         *  java.time.instant 클래스에서는 기계적인 관점으로 시간을 표현함
         *  유닉스 에포크 시간을 기준으로 특정 지점까지의 시간을 초로 표현한다.
         *  Instant 클래스는 나노초의 정밀도를 제공한다.
         */
        // 전부 1970-01-01T00:00:03Z 를 표현하는 방식이다.
        Instant instant = Instant.ofEpochSecond(3);
        System.out.println("instant = " + instant);  // 1970-01-01T00:00:03Z
        Instant instant1 = Instant.ofEpochSecond(3, 0);
        System.out.println("instant1 = " + instant1);
        Instant.ofEpochSecond(2, 1_000_000_000);
        Instant.ofEpochSecond(4, -1_000_000_000);

        Instant now = Instant.now();
        System.out.println("now = " + now);     // 2023-08-19T16:44:09.023312800Z
//        int day1 = Instant.now().get(ChronoField.DAY_OF_MONTH);  // 에러발생! 사람이 읽을수 있는 시간을 정보를 제공하지 않는다.
//        System.out.println("day1 = " + day1);

        /**
         * Duration, Period
         */
        // Duration 은 LocalDateTime, LocalTime, Instant 로 만들수 있다.
        Duration.between(LocalTime.of(13, 40, 20), LocalTime.of(13, 40, 30));
        Duration.between(localDateTime, localDateTime1);
        Duration.between(instant, instant1);

        /**
         *
         * Period 클레스는 팩토리 메서드 between을 이용하여 두 LocalDate 의 차이를 확인할 수있다.
          */
        Period.between(LocalDate.of(2017, 9, 11), LocalDate.of(2017, 9, 20));

        Duration duration = Duration.ofMinutes(3);
        System.out.println("duration = " + duration);
        Duration duration1 = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println("duration1 = " + duration1);

        Period period = Period.ofDays(10);
        System.out.println("period = " + period);
        Period period1 = Period.ofWeeks(3);
        System.out.println("period1 = " + period1);
        Period period2 = Period.of(2, 6, 1);
        System.out.println("period2 = " + period2);
    }
}
