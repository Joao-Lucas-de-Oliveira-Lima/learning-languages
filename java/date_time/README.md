# Java Time API

## Introduction
The Java date and time API was introduced in Java 8 to replace the obsolete `Date` and `Calendar` classes. The new API classes conform to the ISO 8601 standard. It offers immutable data types, support for nanosecond precision, and better handling of time zones.

## Main API Classes

- `LocalDate` - represents a date without time (e.g., 2025-03-16)
- `LocalTime` - represents a time without date (e.g., 23:48:38.542324800)
- `LocalDateTime` - represents both date and time without time zone (e.g., 2025-03-16T23:48:38.542324800)
- `Instant` - represents a moment in UTC time (e.g., 2025-03-17T02:48:38.541319600Z)
- `OffsetDateTime` - represents date and time with an offset from UTC (e.g., 2025-03-16T23:48:38.541319600-03:00)
- `ZonedDateTime` - represents date and time with a time zone (e.g., 2025-03-16T23:48:38.541319600-03:00[America/Sao_Paulo])
- `ZoneId` - represents a time zone identifier (e.g., Asia/Tokyo)
- `ZoneOffset` - represents a fixed offset from UTC (e.g., +18:00)
- `Duration` - represents a time-based amount (e.g., PT51H4M)
- `Period` - represents a date-based amount (e.g., P2Y3M4D)
- `ChronoUnit` - enumeration of date-time units

## ISO 8601 Format
The ISO 8601 standard format used by the Java Time API follows the pattern: `yyyy-MM-ddTHH:mm:ss.SSSSSSSSSZ`
This format starts with the largest time unit and proceeds to the smallest.

## Immutability
All classes in the Java Time API are immutable. Once created, their values cannot be changed. Methods like `plus` and `minus` return new objects rather than modifying the original.

```java
// Date and time addition/subtraction operations
// `plus` and `minus` methods are immutable: they return a new instance without modifying the original object.

LocalDateTime now = LocalDateTime.now(); // Captures the current date and time

// Adding 5 days to the current date/time
LocalDateTime fiveDaysLater = now.plus(Duration.ofDays(5));
// Alternative approach
LocalDateTime fiveDaysLaterAlt = now.plusDays(5);

// Subtracting 5 days from the current date/time
LocalDateTime fiveDaysAgo = now.minus(Duration.ofDays(5));
// Alternative approach
LocalDateTime fiveDaysAgoAlt = now.minusDays(5);

// Displaying the results
System.out.println("Current Date/Time: " + now);
System.out.println("Five Days Later: " + fiveDaysLater);
System.out.println("Five Days Ago: " + fiveDaysAgo);
```

## LocalDate, LocalTime, and LocalDateTime
These classes store date and time without an offset or time zone. They represent "local" time, which has no inherent global meaning without additional context.

### When to use:
- For globally recognized dates like Christmas or New Year
- For local schedules where time zone policies don't apply
- When time zones are unknown or irrelevant (though this should be avoided when possible)

### Example usage:
```java
LocalDate localDate = LocalDate.of(2025, Month.APRIL, 20);
LocalTime localTime = LocalTime.of(23, 59, 59);
LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

// Current date/time
LocalDate today = LocalDate.now();
LocalTime currentTime = LocalTime.now();
LocalDateTime currentDateTime = LocalDateTime.now();
```

## Instant, ZonedDateTime, OffsetDateTime, ZoneId, and ZoneOffset

- `Instant`: Represents a precise moment on the timeline in UTC (Greenwich/Zulu time)
- `OffsetDateTime`: Represents a date-time with an offset from UTC (like +02:00 or -05:00)
- `ZonedDateTime`: Represents a date-time with a time zone ID, accounting for time zone rules like DST
- `ZoneId`: Represents a time zone identifier (like "America/New_York" or "Europe/Paris")
- `ZoneOffset`: Represents just the offset from UTC without additional time zone rules

### Example usage:
```java
// Get current values
Instant now = Instant.now();
ZoneOffset offset = ZoneOffset.ofHours(-3);
OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(now, offset);
ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/London"));

// Explicit creation
Instant specificInstant = Instant.parse("2025-03-17T12:30:45.123456Z");
ZoneId nyZone = ZoneId.of("America/New_York");
ZonedDateTime nyTime = ZonedDateTime.of(
    LocalDateTime.of(2025, 3, 17, 8, 30, 45),
    nyZone
);

// Display values
System.out.println("UTC time (Instant): " + now); // Output: 2025-03-17T12:30:45.123456Z
System.out.println("With offset: " + offsetDateTime); // Output: 2025-03-17T09:30:45.123456-03:00
System.out.println("With zone: " + zonedDateTime); // Output: 2025-03-17T12:30:45.123456Z[Europe/London]
```

## Duration and Period
These classes measure time intervals. They follow ISO 8601 notation:
- Format: `P[n]Y[n]M[n]DT[n]H[n]M[n]S`
- `P` indicates a period, `T` separates date and time parts

### Differences:
- `Duration`: Time-based amount (hours, minutes, seconds)
- `Period`: Date-based amount (years, months, days)

### Example usage:
```java
// Creating Durations
Duration twoHours = Duration.ofHours(2);
Duration complexDuration = Duration.parse("P2DT3H4M"); // 2 days, 3 hours, 4 minutes
Duration simpleTime = Duration.parse("PT3H4M"); // 3 hours, 4 minutes

// Creating Periods
Period twoMonths = Period.ofMonths(2);
Period complexPeriod = Period.of(1, 2, 15); // 1 year, 2 months, 15 days
Period untilDate = Period.between(LocalDate.now(), LocalDate.of(2025, 10, 10));

// Displaying values
System.out.println("Two hours: " + twoHours); // PT2H
System.out.println("Complex duration: " + complexDuration); // P2DT3H4M
System.out.println("Two months: " + twoMonths); // P2M
System.out.println("Until Oct 10, 2025: " + untilDate); // Will vary based on current date
```

## Formatting and Parsing
The `DateTimeFormatter` class allows customizing how date-time objects are converted to and from strings.

### Formatting example:
```java
// Using predefined formatters
LocalDateTime now = LocalDateTime.now();
String basicIsoDate = DateTimeFormatter.BASIC_ISO_DATE.format(now);
String iso = DateTimeFormatter.ISO_DATE_TIME.format(now);

// Creating custom formatters
DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
String formattedDate = customFormatter.format(now);

System.out.println("Basic ISO format: " + basicIsoDate); // 20250317
System.out.println("ISO format: " + iso); // 2025-03-17T13:45:30.123456
System.out.println("Custom format: " + formattedDate); // 17/03/2025 13:45:30
```

### Parsing example:
```java
// Parse from string to date-time objects
String dateText = "17/03/2025 13:45:30";
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
LocalDateTime parsedDate = LocalDateTime.parse(dateText, formatter);

// Parse using standard formats
LocalDate isoDate = LocalDate.parse("2025-03-17");
Instant instant = Instant.parse("2025-03-17T13:45:30Z");

System.out.println("Parsed date: " + parsedDate);
System.out.println("ISO parsed date: " + isoDate);
System.out.println("Parsed instant: " + instant);
```

## ChronoUnit
`ChronoUnit` provides standardized time units for calculating differences between dates and times.

### Example usage:
```java
LocalDate today = LocalDate.now();
LocalDate christmas = LocalDate.of(2025, 12, 25);
LocalTime now = LocalTime.now();
LocalTime endOfDay = LocalTime.of(23, 0);

// Calculate differences
long daysBetween = ChronoUnit.DAYS.between(today, christmas);
long hoursBetween = ChronoUnit.HOURS.between(now, endOfDay);
long minutesBetween = ChronoUnit.MINUTES.between(now, endOfDay);

System.out.println("Days until Christmas: " + daysBetween);
System.out.println("Hours until end of day: " + hoursBetween);
System.out.println("Minutes until end of day: " + minutesBetween);
```

## Common ChronoUnit Values
| Type | Example | Description |
|------|---------|-------------|
| `NANOS` | `ChronoUnit.NANOS` | Nanoseconds |
| `MICROS` | `ChronoUnit.MICROS` | Microseconds |
| `MILLIS` | `ChronoUnit.MILLIS` | Milliseconds |
| `SECONDS` | `ChronoUnit.SECONDS` | Seconds |
| `MINUTES` | `ChronoUnit.MINUTES` | Minutes |
| `HOURS` | `ChronoUnit.HOURS` | Hours |
| `HALF_DAYS` | `ChronoUnit.HALF_DAYS` | Half-days (12 hours) |
| `DAYS` | `ChronoUnit.DAYS` | Days |
| `WEEKS` | `ChronoUnit.WEEKS` | Weeks |
| `MONTHS` | `ChronoUnit.MONTHS` | Months |
| `YEARS` | `ChronoUnit.YEARS` | Years |
| `DECADES` | `ChronoUnit.DECADES` | Decades |
| `CENTURIES` | `ChronoUnit.CENTURIES` | Centuries |
| `MILLENNIA` | `ChronoUnit.MILLENNIA` | Millennia |
| `ERAS` | `ChronoUnit.ERAS` | Eras |
| `FOREVER` | `ChronoUnit.FOREVER` | Forever (essentially infinity) |