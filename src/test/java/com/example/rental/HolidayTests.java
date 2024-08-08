package com.example.rental;

import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HolidayTests {
    @Test
    void dateRange() {
        List<String> expectedHolidays =
                List.of(
                        "2021-07-05",
                        "2021-09-06",
                        "2022-07-04",
                        "2022-09-05",
                        "2023-07-04",
                        "2023-09-04",
                        "2024-07-04",
                        "2024-09-02",
                        "2025-07-04",
                        "2025-09-01",
                        "2026-07-03",
                        "2026-09-07",
                        "2027-07-05",
                        "2027-09-06",
                        "2028-07-04",
                        "2028-09-04");
        for (LocalDate day = LocalDate.of(2021, 1, 1);
                day.getYear() < 2029;
                day = day.plusDays(1)) {
            String text = day.toString();
            boolean expected = expectedHolidays.contains(text);
            Assertions.assertThat(Holiday.matchAny(day)).as(text).isEqualTo(expected);
        }
    }
}
