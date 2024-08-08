package com.example.rental;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Console Checkout UI - for development testing */
@Component
public class InteractiveCheckout {
    @Autowired private CheckoutFactoryBean checkoutFactoryBean;
    private DateTimeFormatter dateParser = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

    public void checkout(BufferedReader reader, PrintStream out) throws Exception {
        Checkout checkout = checkoutFactoryBean.getObject();

        // clear the screen and print banner
        out.print("\033[2J\033[H");
        out.println(
                "--------------------------------------------------------------------------------");
        out.println("Checkout");
        out.println(
                "--------------------------------------------------------------------------------");

        while (true) {
            out.print("Tool Code: ");
            String toolCode = reader.readLine().trim().toUpperCase();
            try {
                checkout.setToolCode(toolCode);
            } catch (IllegalArgumentException except) {
                out.println(except.getMessage());
                continue;
            }
            break;
        }

        while (true) {
            out.print("Rental day count: ");
            try {
                checkout.setRentalDayCount(readInt(reader, 0));
            } catch (IllegalArgumentException except) {
                out.println(except.getMessage());
                continue;
            }
            break;
        }

        while (true) {
            out.print("Discount percent: ");
            try {
                checkout.setDiscountPercent(readInt(reader, -1));
            } catch (IllegalArgumentException except) {
                out.println(except.getMessage());
                continue;
            }
            break;
        }

        while (true) {
            out.print("Check out date (M/D/YY): ");
            String text = reader.readLine().trim();
            try {
                checkout.setCheckoutDate(LocalDate.from(dateParser.parse(text)));
            } catch (DateTimeException e) {
                out.println("Invalid date \"" + text + "\"");
                continue;
            }
            break;
        }

        RentalAgreement agreement = checkout.buildRentalAgreement();
        out.println(
                "--------------------------------------------------------------------------------");
        out.println("Rental Agreement");
        out.println(
                "--------------------------------------------------------------------------------");
        agreement.print(out);
        out.println(
                "--------------------------------------------------------------------------------");
    }

    public void checkoutLoop(InputStream in, PrintStream out) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        boolean exit = false;
        while (!exit) {
            checkout(reader, out);

            while (true) {
                out.print("Continue? [Y|n]: ");
                String text = reader.readLine().trim().toUpperCase();
                if (text.equals("") || text.equals("Y") || text.equals("N")) {
                    exit = text.equals("N");
                    break;
                }
                out.println("Invalid response");
            }
        }
    }

    private int readInt(BufferedReader reader, int fallback) throws IOException {
        int result = fallback;
        String text = reader.readLine().trim();
        try {
            result = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            // just return `fallback`
        }
        return result;
    }
}
