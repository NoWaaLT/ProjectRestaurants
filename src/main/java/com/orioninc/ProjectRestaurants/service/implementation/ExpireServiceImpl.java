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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
  public void saveExpire(Product product, Float productBatch) {
    if (product.getProductExpiration() > 0) {
      expireRepository.save(
          ExpireMapper.INSTANCE.expireRequestDtoToExpire(product, productBatch));
    }
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public Expire updateProductExpire(ExpireRequestDto expireRequestDTO) {
    Expire existingExpire =
        expireRepository
            .findById(expireRequestDTO.id())
            .orElseThrow(
                () -> new ExpireNotFoundException(PRODUCT_EXPIRE_BY_ID, expireRequestDTO.id()));

    existingExpire.setId(expireRequestDTO.id());
    existingExpire.setExpireDate(ExpireDateUtil.expirationDate(expireRequestDTO.expireDuration()));

    Product product =
        productRepository
            .findById(expireRequestDTO.productId())
            .orElseThrow(
                () ->
                    new ProductNotFoundException(
                        PRODUCT_BY_ID_NOT_FOUND, expireRequestDTO.productId()));

    existingExpire.setProduct(product);
    existingExpire.setBatchQuantity(expireRequestDTO.batchQuantity());

    return expireRepository.save(existingExpire);
  }

  @Transactional
  @Override
  public void deleteProductExpire(long id) {
    expireRepository.deleteById(id);
  }

  //  @Override
  //  public Optional<List<ExpireResponseDTO>> findExpiresByProductId(Long id) {
  //    return expireRepository.findExpiresByProductId(id);
  //  }
}
