package com.orioninc.ProjectRestaurants.repository.impl;

import com.orioninc.ProjectRestaurants.model.QExpire;
import com.orioninc.ProjectRestaurants.model.QProduct;
import com.orioninc.ProjectRestaurants.model.QRole;
import com.orioninc.ProjectRestaurants.repository.BaseRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

// TODO To many dependencies

public abstract class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
    implements BaseRepository<T, ID> {

  EntityManager entityManager; // May be default
  protected final JPAQueryFactory jpaQueryFactory;

  protected final QRole role = QRole.role;
  protected final QExpire expire = QExpire.expire;
  protected final QProduct product = QProduct.product;

  protected BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
    super(domainClass, entityManager);
    this.entityManager = entityManager;
    this.jpaQueryFactory = new JPAQueryFactory(entityManager);
  }

  @Override
  public T findByIdMandatory(ID id) throws IllegalArgumentException {
    return findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Entity not found with id " + id));
  }
}
