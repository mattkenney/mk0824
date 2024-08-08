package com.example.rental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

/** Checkout class - RentalAgreement builder */
public class Checkout {
    private final ToolData toolData;

    // initialize with blank data that still satisfies invariants - these values will be overwritten
    private Tool tool = new Tool();
    private int rentalDayCount = 1;
    private int discountPercent = 0;
    private LocalDate checkoutDate = LocalDate.now();

    public Checkout(ToolData toolData) {
        if (toolData == null) {
            throw new NullPointerException("Checkout toolData must not be null");
        }

        this.toolData = toolData;
    }

    /**
     * Builds a RentalAgreement according to the current toolCode, rentalDayCount, discountPercent,
     * and checkoutDate.
     */
    public RentalAgreement buildRentalAgreement() {
        RentalAgreement result = new RentalAgreement();

        // Specified at checkout
        result.setCheckoutDate(getCheckoutDate())
                .setDiscountPercent(getDiscountPercent())
                .setRentalDayCount(getRentalDayCount());

        // From tool info
        result.setDailyRentalCharge(getTool().getDailyRate())
                .setToolBrand(getTool().getToolBrand())
                .setToolCode(getTool().getToolCode())
                .setToolType(getTool().getToolType());

        // Calculated values
        LocalDate dueDate = calculateDueDate();
        int chargeDayCount = calculateChargeDayCount();
        BigDecimal preDiscountCharge = calculatePreDiscountCharge(chargeDayCount);
        BigDecimal discountAmount = calculateDiscountAmount(preDiscountCharge);
        BigDecimal finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);
        result.setChargeDayCount(chargeDayCount)
                .setDiscountAmount(discountAmount)
                .setDueDate(dueDate)
                .setFinalCharge(finalCharge)
                .setPreDiscountCharge(preDiscountCharge);

        return result;
    }

    private int calculateChargeDayCount() {
        int result = 0;

        LocalDate day = checkoutDate;
        for (int i = 0; i < rentalDayCount; i++) {
            day = day.plusDays(1);
            if (Holiday.matchAny(day)) {
                if (getTool().getIsChargeHoliday()) {
                    result++;
                }
            } else if (day.getDayOfWeek() == DayOfWeek.SATURDAY
                    || day.getDayOfWeek() == DayOfWeek.SUNDAY) {
                if (getTool().getIsChargeWeekend()) {
                    result++;
                }
            } else {
                if (getTool().getIsChargeWeekday()) {
                    result++;
                }
            }
        }

        return result;
    }

    private BigDecimal calculateDiscountAmount(BigDecimal preDiscountCharge) {
        return preDiscountCharge
                .multiply(new BigDecimal(getDiscountPercent()))
                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }

    private LocalDate calculateDueDate() {
        return getCheckoutDate().plusDays(getRentalDayCount());
    }

    private BigDecimal calculateFinalCharge(
            BigDecimal preDiscountCharge, BigDecimal discountAmount) {
        return preDiscountCharge.subtract(discountAmount);
    }

    private BigDecimal calculatePreDiscountCharge(int chargeDayCount) {
        return getTool().getDailyRate().multiply(new BigDecimal(chargeDayCount));
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getRentalDayCount() {
        return rentalDayCount;
    }

    public Tool getTool() {
        return tool;
    }

    /**
     * Set the check out date
     *
     * @param checkoutDate must not be null
     * @throws NullPointerException
     */
    public Checkout setCheckoutDate(LocalDate checkoutDate) {
        if (checkoutDate == null) {
            throw new NullPointerException("Check out date must not be null");
        }

        this.checkoutDate = checkoutDate;

        return this;
    }

    /**
     * Set the discount percent
     *
     * @param discountPercent must be in the range 0-100
     * @throws IllegalArgumentException
     */
    public Checkout setDiscountPercent(int discountPercent) {
        if (discountPercent < 0 || 100 < discountPercent) {
            throw new IllegalArgumentException("Discount percent must be in the range 0-100");
        }

        this.discountPercent = discountPercent;

        return this;
    }

    /**
     * Set the rental day count
     *
     * @param rentalDayCount must be 1 or greater
     * @throws IllegalArgumentException
     */
    public Checkout setRentalDayCount(int rentalDayCount) {
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException("Rental day count must be 1 or greater");
        }

        this.rentalDayCount = rentalDayCount;

        return this;
    }

    /**
     * Set the tool code
     *
     * @param toolCode must be one of the defined tool codes
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public Checkout setToolCode(String toolCode) {
        if (toolCode == null) {
            throw new NullPointerException("Tool code must not be null");
        }

        Tool tool = toolData.getTool(toolCode);

        if (tool == null) {
            throw new IllegalArgumentException("Unknown tool code");
        }

        this.tool = tool;

        return this;
    }
}
