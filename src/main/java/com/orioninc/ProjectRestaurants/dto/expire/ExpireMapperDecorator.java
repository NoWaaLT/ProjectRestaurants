package com.orioninc.ProjectRestaurants.dto.expire;

import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.utils.ExpireDateUtil;

import java.util.Date;

public abstract class ExpireMapperDecorator implements ExpireMapper {

  ExpireMapper expireMapper;

  protected ExpireMapperDecorator(ExpireMapper expireMapper) {
    this.expireMapper = expireMapper;
  }

  @Override
  public Expire expireRequestDtoToExpire(Product product, Float productBatch) {
    Expire expire = new Expire();
    expire.setProduct(product);
    Date newDate = ExpireDateUtil.getExpireDate(product.getProductExpiration(), new Date());
    expire.setExpireDate(newDate);
    expire.setBatchQuantity(productBatch);
    expire.setRemovedProduct(false);

    return expire;
  }
}
