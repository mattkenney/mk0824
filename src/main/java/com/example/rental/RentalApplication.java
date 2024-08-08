package com.example.rental;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentalApplication implements CommandLineRunner {
    @Autowired private InteractiveCheckout checkout;

    private static Logger LOG = LoggerFactory.getLogger(RentalApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RentalApplication.class, args);
    }

    public void run(String... args) throws Exception {
        if (System.console() == null) {
            LOG.info("Not connected to console - not running InteractiveCheckout");
        } else {
            checkout.checkoutLoop(System.in, System.out);
        }
    }
}
