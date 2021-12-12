package net.binis.example.core.tools;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

import static java.util.Objects.nonNull;

public class Time {

    private static Clock clock = Clock.systemUTC();

    public static final long ONE_DAY = 1000 * 60 * 60 * 24L;

    private Time() {
        //Do nothing
    }

    public static void init(Clock clock) {
        Time.clock = clock;
    }

    public static OffsetDateTime now() {
        return Instant.now(clock).atOffset(ZoneOffset.UTC);
    }

    public static OffsetDateTime of(Long milli) {
        return Instant.ofEpochMilli(milli).atOffset(ZoneOffset.UTC);
    }

    public static OffsetDateTime of(int year, int month, int day) {
        return OffsetDateTime.of(year, month, day, 0, 0, 0, 0, ZoneOffset.UTC);
    }

    public static OffsetDateTime ofEpochSecond(Long epochSeconds) {
        return OffsetDateTime.of(LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.UTC), ZoneOffset.UTC);
    }

    public static OffsetDateTime atStartOfDay() {
        return atStartOfDay(now());
    }

    public static OffsetDateTime atStartOfDay(Long milli) {
        return of(milli / ONE_DAY * ONE_DAY);
    }

    public static OffsetDateTime atStartOfDay(OffsetDateTime date) {
        return atStartOfDay(toMillis(date));
    }

    public static OffsetDateTime atEndOfDay() {
        return atEndOfDay(now());
    }

    public static OffsetDateTime atEndOfDay(Long milli) {
        return of((((milli + ONE_DAY) / ONE_DAY) * ONE_DAY) -1);
    }

    public static OffsetDateTime atEndOfDay(OffsetDateTime date) {
        return atEndOfDay(date.toInstant().toEpochMilli());
    }

    public static OffsetDateTime atStartOfYear() {
        return now().with(TemporalAdjusters.firstDayOfYear());
    }

    public static long toMillis(OffsetDateTime date) {
        return date.toInstant().toEpochMilli();
    }

    public static long toMillis() {
        return now().toInstant().toEpochMilli();
    }

    public static boolean isLastDayOfTheMonth(OffsetDateTime date) {
        return atStartOfDay(date).equals(date.with(TemporalAdjusters.lastDayOfMonth()));
    }

    public static boolean isToday(OffsetDateTime date) {
        return atStartOfDay(date).equals(atStartOfDay());
    }

    public static OffsetDateTime of(String time) {
        return nonNull(time) ? OffsetDateTime.parse(time) : null;
    }

    public static OffsetDateTime atStartOfMonth() {
        return atStartOfDay().with(TemporalAdjusters.firstDayOfMonth());
    }

    public static OffsetDateTime atStartOfMonth(OffsetDateTime date) {
        return atStartOfDay(date).with(TemporalAdjusters.firstDayOfMonth());
    }

    public static OffsetDateTime never() {
        return Time.of(294276, 12, 31);
    }
}
