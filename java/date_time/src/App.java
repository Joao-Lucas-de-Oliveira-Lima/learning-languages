import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        /*
         * LocalDate, LocalDateTime, and LocalTime store date and time without an Offset or Timezone.
         * These types do not represent a specific point on the timeline without additional context.
         */

        // Using LocalDate and LocalTime to store date and time

        // The month can be accessed using an enum
        LocalDate localDate = LocalDate.of(2025, Month.APRIL, 20);

        // Storing time
        LocalTime localTime = LocalTime.of(23, 59, 59);

        // Getting the current time
        LocalTime localTimeNow = LocalTime.now();

        // Getting the current date
        LocalDate localDateNow = LocalDate.now();

        // LocalDateTime stores both date and time
        LocalDateTime localDateTime = LocalDateTime.of(localDateNow, localTimeNow);

        // Getting the current LocalDateTime
        LocalDateTime localDateTimeNow = LocalDateTime.now();

        // Printing all available time zone identifiers
        // To retrieve a specific time zone, use ZoneId.of("ZoneId");
        for (String zone : ZoneId.getAvailableZoneIds()) {
            System.out.println(zone);
        }

        System.out.printf("LocalDateTime: %s | Current LocalDateTime: %s%n", localDateTime, localDateTimeNow);

        // Getting the current date and time in a specific time zone
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, ZoneId.of("Asia/Tokyo"));

        /*
         * All time-related objects (LocalTime, LocalDate, LocalDateTime, ZonedDateTime, Instant and OffsetDateTime) are immutable.
         * Once created, their values cannot be changed.
         * Methods like plus() or minus() create new objects with the updated date/time values.
         */

        // Date/Time Operations
        // Addition
        LocalDateTime updatedDateTime = localDateTime.plusDays(4); // Adds four days

        // Subtraction
        LocalDate updatedDate = localDate.minusMonths(2); // Subtracts two months

        // Extracting individual values from LocalDateTime
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        int nanoSecond = localDateTime.getNano();

        // Comparing time values
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);

        if (today.isAfter(yesterday)) {
            System.out.println("Today: " + today);
        }

        // Parsing an ISO 8601 formatted String into OffsetDateTime
        String timeString = "2024-04-12T23:10:50.1234Z";
        OffsetDateTime parsedInstant = OffsetDateTime.parse(timeString, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(parsedInstant);

        // Creating a ZonedDateTime with a specific zone
        ZonedDateTime zonedDateTimeExample = ZonedDateTime.of(2025, 2, 21, 15, 4, 30, 133330, ZoneId.of("US/Pacific"));
        System.out.println(zonedDateTimeExample);

        // Duration - Measuring time between two dates
        ZonedDateTime time1 = ZonedDateTime.now();
        ZonedDateTime time2 = ZonedDateTime.of(2026, 12, 12, 12, 12, 12, 2131233, ZoneId.of("US/Pacific"));

        // Calculating duration between two time points (returns time-based difference)
        Duration duration = Duration.between(time1, time2);
        System.out.println(duration);

        // Creating a custom Duration (e.g., 1000 days)
        Duration customDuration = Duration.of(1000, ChronoUnit.DAYS);
        System.out.println(customDuration);

        // Period - Storing date-based differences
        LocalDate futureDate = LocalDate.of(2027, 11, 30);
        Period periodBetween = Period.between(localDateNow, futureDate);
        System.out.println(periodBetween);


        // Date and time addition/subtraction operations
        // `plus` and `minus` methods are immutable: they return a new instance without modifying the original object.

        LocalDateTime now = LocalDateTime.now(); // Captures the current date and time

        // Adding 5 days to the current date/time
        LocalDateTime fiveDaysLater = now.plus(Duration.ofDays(5));

        // Subtracting 5 days from the current date/time
        LocalDateTime fiveDaysAgo = now.minus(Duration.ofDays(5));

        // Displaying the results
        System.out.println("Current Date/Time: " + now);
        System.out.println("Five Days Later: " + fiveDaysLater);
        System.out.println("Five Days Ago: " + fiveDaysAgo);


        /*
         * Summary of Java Time API Components:
         * - Instant: Represents a moment in UTC.
         * - OffsetDateTime: Represents a moment with an offset.
         * - ZonedDateTime: Represents a moment with a time zone (offset + region rules, e.g., daylight saving time).
         * - LocalDateTime, LocalDate, LocalTime: Represent a moment without an offset or time zone.
         *   ("Local" means any locality or every locality.)
         * - Duration: Represents an amount of time (time-based measurement).
         * - Period: Represents an amount of time in date units.
         * - ChronoUnit: An enumeration representing units of time (days, hours, minutes, etc.).
         */
    }
}
