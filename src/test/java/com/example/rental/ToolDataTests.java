package com.example.rental;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolDataTests {
    @Autowired private ToolData toolData;

    @Test
    void getJAKD() {
        String toolCode = "JAKD";
        Tool tool = toolData.getTool(toolCode);
        Assertions.assertThat(tool).as(toolCode).isNotNull();
        Assertions.assertThat(tool.getDailyRate())
                .as("getDailyRate")
                .isEqualTo(new BigDecimal("2.99"));
        Assertions.assertThat(tool.getIsChargeHoliday()).as("getIsChargeHoliday").isEqualTo(false);
        Assertions.assertThat(tool.getIsChargeWeekday()).as("getIsChargeWeekday").isEqualTo(true);
        Assertions.assertThat(tool.getIsChargeWeekend()).as("getIsChargeWeekend").isEqualTo(false);
        Assertions.assertThat(tool.getToolBrand()).as("getToolBrand").isEqualTo("DeWalt");
        Assertions.assertThat(tool.getToolCode()).as("getToolCode").isEqualTo(toolCode);
        Assertions.assertThat(tool.getToolType()).as("getToolType").isEqualTo("Jackhammer");
    }
}
