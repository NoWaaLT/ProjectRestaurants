package com.orioninc.ProjectRestaurants.repository.impl;

import com.orioninc.ProjectRestaurants.model.QExpire;
import com.orioninc.ProjectRestaurants.model.QProduct;
import com.orioninc.ProjectRestaurants.repository.CustomRepository;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;

public class CustomRepositoryImpl implements CustomRepository {

    private final EntityManager entityManager;

    private final QExpire expire = QExpire.expire;
    private final QProduct product = QProduct.product;

    CustomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void setAmountAndRemoved(float batch, boolean status, Long id) {
        JPAUpdateClause updateClause = new JPAUpdateClause(entityManager, expire);

        updateClause.where(expire.id.eq(id))
                .set(expire.batchQuantity, batch)
                .set(expire.removedProduct, status)
                .execute();
        }

    @Override
    public void setProductBalance(float balance, Long id) {
        JPAUpdateClause updateClause = new JPAUpdateClause(entityManager, product);

        updateClause.where(product.id.eq(id))
                .set(product.productBalance, balance)
                .execute();
    }

    @Override
    public void setProductExpire(int days, Long id) {
        JPAUpdateClause updateClause = new JPAUpdateClause(entityManager, product);

        updateClause.where(product.id.eq(id))
                .set(product.productExpiration, days)
                .execute();

    }
}
