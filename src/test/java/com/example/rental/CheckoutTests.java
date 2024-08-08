package com.example.rental;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CheckoutTests {
    @Autowired private CheckoutFactoryBean checkoutFactoryBean;

    @Test
    void checkout() throws Exception {
        String toolCode = "JAKD";
        int rentalDayCount = 7;
        int discountPercent = 5;
        LocalDate checkoutDate = LocalDate.of(2024, 7, 1);

        Checkout checkout = checkoutFactoryBean.getObject();
        Assertions.assertThat(checkout).as("getObject").isNotNull();

        checkout.setToolCode(toolCode);
        checkout.setRentalDayCount(rentalDayCount);
        checkout.setDiscountPercent(discountPercent);
        checkout.setCheckoutDate(checkoutDate);

        RentalAgreement agreement = checkout.buildRentalAgreement();
        Assertions.assertThat(agreement).as("buildRentalAgreement").isNotNull();
        Assertions.assertThat(agreement.getChargeDayCount()).as("getChargeDayCount").isEqualTo(4);
        Assertions.assertThat(agreement.getCheckoutDate())
                .as("getCheckoutDate")
                .isEqualTo(checkoutDate);
        Assertions.assertThat(agreement.getDailyRentalCharge())
                .as("getDailyRentalCharge")
                .isEqualTo(new BigDecimal("2.99"));
        Assertions.assertThat(agreement.getDiscountAmount())
                .as("getDiscountAmount")
                .isEqualTo(new BigDecimal("0.60"));
        Assertions.assertThat(agreement.getDiscountPercent())
                .as("getDiscountPercent")
                .isEqualTo(discountPercent);
        Assertions.assertThat(agreement.getDueDate())
                .as("getDueDate")
                .isEqualTo(LocalDate.of(2024, 7, 8));
        Assertions.assertThat(agreement.getFinalCharge())
                .as("getFinalCharge")
                .isEqualTo(new BigDecimal("11.36"));
        Assertions.assertThat(agreement.getPreDiscountCharge())
                .as("getPreDiscountCharge")
                .isEqualTo(new BigDecimal("11.96"));
        Assertions.assertThat(agreement.getRentalDayCount())
                .as("getRentalDayCount")
                .isEqualTo(rentalDayCount);
        Assertions.assertThat(agreement.getToolBrand()).as("getToolBrand").isEqualTo("DeWalt");
        Assertions.assertThat(agreement.getToolCode()).as("getToolCode").isEqualTo(toolCode);
        Assertions.assertThat(agreement.getToolType()).as("getToolType").isEqualTo("Jackhammer");
    }

    @Test
    void checkoutExceptions() throws Exception {
        Checkout checkout = checkoutFactoryBean.getObject();

        Assertions.assertThatNullPointerException()
                .isThrownBy(
                        () -> {
                            checkout.setToolCode(null);
                        });
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(
                        () -> {
                            checkout.setToolCode("BUGUS");
                        });
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(
                        () -> {
                            checkout.setRentalDayCount(0);
                        });
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(
                        () -> {
                            checkout.setDiscountPercent(-1);
                        });
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(
                        () -> {
                            checkout.setDiscountPercent(101);
                        });
        Assertions.assertThatNullPointerException()
                .isThrownBy(
                        () -> {
                            checkout.setCheckoutDate(null);
                        });
    }
}
