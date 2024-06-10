package com.orioninc.ProjectRestaurants.restaurant;

import com.orioninc.ProjectRestaurants.order.OrderProcessing;

public abstract class Restaurant implements OrderProcessing {
    public String name;

    public abstract void sendOrder();
}
