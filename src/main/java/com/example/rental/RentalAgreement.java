package com.example.rental;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * RentalAgreement stores and formats rental agreement values. The class DOES NOT enforce validity
 * or consistency of its attributes.
 */
public class RentalAgreement {
    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("MM/dd/uu", Locale.US);

    private int chargeDayCount;
    private LocalDate checkoutDate;
    private BigDecimal dailyRentalCharge;
    private BigDecimal discountAmount;
    private int discountPercent;
    private LocalDate dueDate;
    private BigDecimal finalCharge;
    private BigDecimal preDiscountCharge;
    private int rentalDayCount;
    private String toolBrand;
    private String toolCode;
    private String toolType;

    public int getChargeDayCount() {
        return chargeDayCount;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getRentalDayCount() {
        return rentalDayCount;
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

    public RentalAgreement print() {
        print(System.out);

        return this;
    }

    public RentalAgreement print(PrintStream out) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

        out.printf("Tool code: %s\n", getToolCode())
                .printf("Tool type: %s\n", getToolType())
                .printf("Tool brand: %s\n", getToolBrand())
                .printf("Rental days: %d\n", getRentalDayCount())
                .printf("Check out date: %s\n", dateFormatter.format(getCheckoutDate()))
                .printf("Due date: %s\n", dateFormatter.format(getDueDate()))
                .printf("Daily rental charge: %s\n", currencyFormat.format(getDailyRentalCharge()))
                .printf("Charge days: %d\n", getChargeDayCount())
                .printf("Pre-discount charge: %s\n", currencyFormat.format(getPreDiscountCharge()))
                .printf("Discount percent: %d%%\n", getDiscountPercent())
                .printf("Discount amount: %s\n", currencyFormat.format(getDiscountAmount()))
                .printf("Final charge: %s\n", currencyFormat.format(getFinalCharge()));

        return this;
    }

    public RentalAgreement setChargeDayCount(int chargeDayCount) {
        this.chargeDayCount = chargeDayCount;

        return this;
    }

    public RentalAgreement setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;

        return this;
    }

    public RentalAgreement setDailyRentalCharge(BigDecimal dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;

        return this;
    }

    public RentalAgreement setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;

        return this;
    }

    public RentalAgreement setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;

        return this;
    }

    public RentalAgreement setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;

        return this;
    }

    public RentalAgreement setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;

        return this;
    }

    public RentalAgreement setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;

        return this;
    }

    public RentalAgreement setRentalDayCount(int rentalDayCount) {
        this.rentalDayCount = rentalDayCount;

        return this;
    }

    public RentalAgreement setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;

        return this;
    }

    public RentalAgreement setToolCode(String toolCode) {
        this.toolCode = toolCode;

        return this;
    }

    public RentalAgreement setToolType(String toolType) {
        this.toolType = toolType;

        return this;
    }
}
