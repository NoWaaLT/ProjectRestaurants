package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.expire.*;
import com.orioninc.ProjectRestaurants.exceptions.ExpireNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.ProductNotFoundException;
import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.model.Product;
import com.orioninc.ProjectRestaurants.repository.ExpireRepository;
import com.orioninc.ProjectRestaurants.repository.ProductRepository;
import com.orioninc.ProjectRestaurants.service.ExpireService;
import com.orioninc.ProjectRestaurants.utils.ExpireDateUtil;

import lombok.AllArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static com.orioninc.ProjectRestaurants.enums.AppText.*;

@Service
@AllArgsConstructor
public class ExpireServiceImpl implements ExpireService {

  private final ExpireRepository expireRepository;
  private final ProductRepository productRepository;

  @Transactional(readOnly = true)
  @Override
  public List<ExpireResponseDto> getAllProductExpires() {
    List<Expire> expiresList = expireRepository.findAll();

    if (expiresList.isEmpty()) {
      throw new ExpireNotFoundException(EXPIRES_EMPTY);
    }

    return expiresList.stream().map(ExpireMapper.INSTANCE::expireToExpireResponseDto).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public ExpireResponseDto getProductExpireById(Long id) {
    return ExpireMapper.INSTANCE.expireToExpireResponseDto(
        expireRepository
            .findById(id)
            .orElseThrow(() -> new ExpireNotFoundException(PRODUCT_EXPIRE_BY_ID, id)));
  }

  @Transactional
  @Override
  public Expire saveProductExpire(Expire expire) {
    return expireRepository.save(expire);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED)
  public void saveExpire(@NotNull Product product, Float productBatch) {
    if (product.getProductExpiration() > 0) {
      expireRepository.save(ExpireMapper.INSTANCE.expireRequestDtoToExpire(product, productBatch));
    }
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public Expire updateProductExpire(@NotNull ExpireRequestDto expireRequestDto) {
    long expireId = expireRequestDto.id();
    Expire existingExpire =
        expireRepository
            .findById(expireId)
            .orElseThrow(() -> new ExpireNotFoundException(PRODUCT_EXPIRE_BY_ID, expireId));

    existingExpire.setId(expireId);

    long productId = expireRequestDto.productId();

    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(PRODUCT_BY_ID_NOT_FOUND, productId));

    existingExpire.setProduct(product);

    int expireDuration = expireRequestDto.expireDuration();
    int currentExpireDuration = product.getProductExpiration();
    int diff;

    Date currentExpire = existingExpire.getExpireDate();
    Date newExpireDate;

    if (expireDuration == currentExpireDuration) {
      existingExpire.setExpireDate(currentExpire);
    } else if (expireDuration < currentExpireDuration) {
      diff = currentExpireDuration - expireDuration;
      newExpireDate = ExpireDateUtil.getExpireDate(-diff, currentExpire);
      existingExpire.setExpireDate(newExpireDate);
    } else {
      diff = expireDuration - currentExpireDuration;
      newExpireDate = ExpireDateUtil.getExpireDate(diff, currentExpire);
      existingExpire.setExpireDate(newExpireDate);
    }

    productRepository.setProductExpire(expireDuration, product.getId());

    float amount = expireRequestDto.batchQuantity();
    float currentAmount = existingExpire.getBatchQuantity();
    float currentProductBalance = product.getProductBalance();

    if (amount < currentAmount) {
      float balance = currentAmount - amount;
      productRepository.setProductBalance(currentProductBalance - balance, productId);

      if (currentAmount == 0.0f) {
        existingExpire.setRemovedProduct(true);
      }
    }

    if (amount > currentAmount) {
      float balance = amount - currentAmount;
      productRepository.setProductBalance(currentProductBalance + balance, productId);

      if (amount > 0.0f) {
        existingExpire.setRemovedProduct(false);
      }
    }

    existingExpire.setBatchQuantity(amount);

    return expireRepository.save(existingExpire);
  }

  @Transactional
  @Override
  public void deleteProductExpire(long id) {
    expireRepository.deleteById(id);
  }

  @Override
  public Integer getEarliestExpire(@NotNull List<Expire> listOfExpires) {
    return IntStream.range(0, listOfExpires.size())
        .filter(i -> !listOfExpires.get(i).getRemovedProduct())
        .boxed()
        .min(Comparator.comparing(i -> listOfExpires.get(i).getExpireDate()))
        .stream()
        .findFirst()
        .orElse(-1);
  }

  //  @Override
  //  public Optional<List<ExpireResponseDTO>> findExpiresByProductId(Long id) {
  //    return expireRepository.findExpiresByProductId(id);
  //  }
}
