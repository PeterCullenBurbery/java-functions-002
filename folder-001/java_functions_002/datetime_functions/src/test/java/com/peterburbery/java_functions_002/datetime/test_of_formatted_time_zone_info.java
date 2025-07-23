package com.peterburbery.java_functions_002.datetime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class formatted_time_zone_infoTest {
    @Test
    void timestamp_is_not_empty() {
        assertFalse(formatted_time_zone_info.Get_current_date_timestamp().isEmpty());
    }

    @Test
    void user_prefix_is_present() {
        assertTrue(formatted_time_zone_info.user_timestamped().startsWith("user_"));
    }
}