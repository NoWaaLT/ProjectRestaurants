package com.orioninc.ProjectRestaurants.scheduled;

import com.orioninc.ProjectRestaurants.DTO.expire.ExpireResponseDTO;
import com.orioninc.ProjectRestaurants.service.ProductExpireService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductHasExpired {

  private final ProductExpireService productExpireService;

  public String removeExpiredProducts(List<ExpireResponseDTO> expiredProductsList) {
    if (!expiredProductsList.isEmpty()) {
//      expiredProductsList.forEach();
      return "Products removed";
    } else {
      return "All products up to date";
    }
  }

//  //  @Scheduled(cron = "0 0 0 * * ")
//  public List<ExpireResponseDTO> expiredProducts() {
//    return productExpireService.getAllProductExpires().stream()
//        .filter(
//            (ExpireResponseDTO expireResponseDTO) -> productExpired(expireResponseDTO.expireDate()))
//        .filter(
//            (ExpireResponseDTO expireResponseDTO) -> expireResponseDTO.)
//        .toList();
//  }

  public boolean productExpired(Date expireDate) { // Returns true if expired
    Calendar calendar = Calendar.getInstance();
    return expireDate.before(calendar.getTime());
  }
}
