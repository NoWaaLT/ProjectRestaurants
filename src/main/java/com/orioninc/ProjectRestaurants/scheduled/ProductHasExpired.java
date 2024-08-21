package com.orioninc.ProjectRestaurants.scheduled;

import com.orioninc.ProjectRestaurants.dto.expire.ExpireResponseDto;
import com.orioninc.ProjectRestaurants.service.ExpireService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductHasExpired {

  private final ExpireService expireService;

  public String removeExpiredProducts(List<ExpireResponseDto> expiredProductsList) {
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
