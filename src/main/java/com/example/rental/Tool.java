package com.example.rental;

import java.math.BigDecimal;

/** Represents a rentable tool. */
public class Tool {
    private BigDecimal dailyRate = BigDecimal.ZERO;
    private boolean isChargeHoliday = false;
    private boolean isChargeWeekday = false;
    private boolean isChargeWeekend = false;
    private String toolBrand = "";
    private String toolCode = "";
    private String toolType = "";

    public Tool() {}

    public Tool(Tool source) {
        dailyRate = source.dailyRate;
        isChargeHoliday = source.isChargeHoliday;
        isChargeWeekday = source.isChargeWeekday;
        isChargeWeekend = source.isChargeWeekend;
        toolBrand = source.toolBrand;
        toolCode = source.toolCode;
        toolType = source.toolType;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public boolean getIsChargeHoliday() {
        return isChargeHoliday;
    }

    public boolean getIsChargeWeekday() {
        return isChargeWeekday;
    }

    public boolean getIsChargeWeekend() {
        return isChargeWeekend;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public Tool setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;

        return this;
    }

    public Tool setIsChargeHoliday(boolean isChargeHoliday) {
        this.isChargeHoliday = isChargeHoliday;

        return this;
    }

    public Tool setIsChargeWeekday(boolean isChargeWeekday) {
        this.isChargeWeekday = isChargeWeekday;

        return this;
    }

    public Tool setIsChargeWeekend(boolean isChargeWeekend) {
        this.isChargeWeekend = isChargeWeekend;

        return this;
    }

    public Tool setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;

        return this;
    }

    public Tool setToolCode(String toolCode) {
        this.toolCode = toolCode;

        return this;
    }

    public Tool setToolType(String toolType) {
        this.toolType = toolType;

        return this;
    }

    @Override
    public String toString() {
        return "{toolCode="
                + toolCode
                + ", toolType="
                + toolType
                + ", toolBrand="
                + toolBrand
                + ", dailyRate="
                + dailyRate
                + ", isChargeHoliday="
                + isChargeHoliday
                + ", isChargeWeekday="
                + isChargeWeekday
                + ", isChargeWeekend="
                + isChargeWeekend
                + "}";
    }
}
