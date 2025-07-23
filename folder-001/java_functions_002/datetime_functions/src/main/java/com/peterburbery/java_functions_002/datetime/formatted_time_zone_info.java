package com.peterburbery.java_functions_002.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

/**
 * Provides utilities for formatting time zone-aware timestamps and generating safe identifiers.
 */
public class formatted_time_zone_info {

    /**
     * Returns a formatted timestamp string with time zone, ISO week, and ordinal date info.
     *
     * @return Formatted timestamp string with zone and ISO metadata.
     */
    public static String Get_current_date_timestamp() {
        ZonedDateTime now = ZonedDateTime.now();
        ZoneId tz = now.getZone();

        // Nanoseconds pattern using repeat
        String nanoseconds_pattern = "n".repeat(9);

        // Gregorian calendar parts
        String date_part = now.format(DateTimeFormatter.ofPattern("yyyy-0MM-0dd"));
        String time_part = now.format(DateTimeFormatter.ofPattern("0HH.0mm.0ss." + nanoseconds_pattern));

        // ISO week info
        WeekFields wf = WeekFields.ISO;
        int week = now.get(wf.weekOfWeekBasedYear());
        int weekday = now.get(wf.dayOfWeek());
        int iso_year = now.get(wf.weekBasedYear());

        // Ordinal day (day of year)
        int day_of_year = now.getDayOfYear();

        // Compose output
        String output = String.format(
                "%s %s %04d-W%03d-%03d %04d-%03d",
                date_part, time_part, iso_year, week, weekday, now.getYear(), day_of_year);

        // Insert time zone ID between parts
        output = output.replace(time_part, time_part + " " + tz);

        return output;
    }

    /**
     * Replaces all forward slashes in the input string with the word " slash ".
     *
     * @param input Input string to sanitize.
     * @return String with "/" replaced by " slash ".
     */
    public static String replace_slash(String input) {
        return input.replace("/", " slash ");
    }

    /**
     * Replaces specific special characters (dash, space, period) in the input string with underscores.
     *
     * @param input Input string to transform.
     * @return Modified string with special characters replaced.
     */
    public static String replace_special_chars(String input) {
        String[] targets = { "-", " ", "." };
        for (String ch : targets) {
            input = input.replace(ch, "_");
        }
        return input;
    }

    /**
     * Returns a timestamp string where slashes, dashes, spaces, and dots have been replaced with underscores.
     *
     * @return A safe, underscore-formatted timestamp string.
     */
    public static String safetime_timestamp_of_underscore() {
        return replace_special_chars(replace_slash(Get_current_date_timestamp()));
    }

    /**
     * Returns a username-like string prefixed with "user_" and suffixed with a safe timestamp.
     *
     * @return User-friendly identifier with embedded timestamp.
     */
    public static String user_timestamped() {
        return "user_" + safetime_timestamp_of_underscore();
    }

    /**
     * Main method for demonstration. Prints each transformation step.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String original = Get_current_date_timestamp();
        System.out.println(original); // Step 1: original timestamp

        String no_slash = replace_slash(original);
        System.out.println(no_slash); // Step 2: slash replaced

        String safe = replace_special_chars(no_slash);
        System.out.println(safe); // Step 3: everything underscored

        String username = user_timestamped();
        System.out.println(username); // Final username
    }

    /**
     * Default constructor (not used). Exists to satisfy documentation tools.
     */
    public formatted_time_zone_info() {
        // No instance needed for static utility class
    }

}