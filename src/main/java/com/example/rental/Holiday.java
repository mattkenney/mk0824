package com.example.rental;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * Enum of Holidays. Use the `match` method of the enum constants to test if a `LocalDate` is an
 * occurance of that holiday. Use the static method `matchAny` to test if a `LocalDate` is an
 * occurance of any of the holidays.
 */
public enum Holiday {
    INDEPENDENCE {
        @Override
        public boolean match(LocalDate input) {
            if (input.getMonth() == Month.JULY) {
                if (input.getDayOfMonth() == 3 && input.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    return true;
                }
                if (input.getDayOfMonth() == 4
                        && input.getDayOfWeek() != DayOfWeek.SATURDAY
                        && input.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    return true;
                }
                if (input.getDayOfMonth() == 5 && input.getDayOfWeek() == DayOfWeek.MONDAY) {
                    return true;
                }
            }

            return false;
        }
    },
    LABOR {
        @Override
        public boolean match(LocalDate input) {
            if (input.getMonth() == Month.SEPTEMBER
                    && input.getDayOfMonth() <= 7
                    && input.getDayOfWeek() == DayOfWeek.MONDAY) {
                return true;
            }

            return false;
        }
    },
    ;

    public abstract boolean match(LocalDate input);

    public static boolean matchAny(LocalDate input) {
        for (Holiday elem : values()) {
            if (elem.match(input)) {
                return true;
            }
        }

        return false;
    }
}
