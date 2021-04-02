package com.rnd.corp.springpocketapi.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class FinanceServiceHelper {

    /**
     * Get associated monthlyTransaction's date
     *
     * @param date date to check
     * @return monthlyTransaction's date
     */
    public static Instant getAssociatedMonthDate(final Date date) {
        return Instant.ofEpochMilli(date.getTime())
                      .atZone(ZoneId.systemDefault())
                      .toLocalDateTime()
                      .withDayOfMonth(2)
                      .toInstant(ZoneOffset.UTC);

    }

    /**
     * Set the date to the second day of actual month
     * @return instant
     */
    public static Instant setInitialMonthDate() {
        return LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
                            .withDayOfMonth(2)
                            .toInstant(ZoneOffset.UTC);
    }
}
