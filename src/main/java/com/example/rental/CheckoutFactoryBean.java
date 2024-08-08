package com.example.rental;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory for Checkout instances - allows Spring IoC container to initialize Checkout instances
 * with the ToolData
 */
@Component
public class CheckoutFactoryBean implements FactoryBean<Checkout> {
    @Autowired private ToolData toolData;

    @Override
    public Checkout getObject() throws Exception {
        return new Checkout(toolData);
    }

    @Override
    public Class<?> getObjectType() {
        return Checkout.class;
    }
}
