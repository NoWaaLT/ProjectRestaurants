package com.orioninc.ProjectRestaurants.repository;

public interface CustomRepository {
    void setAmountAndRemoved(float batch, boolean status, Long id);

    void setProductBalance(float balance, Long id);

    void setProductExpire(int days, Long id);
}
