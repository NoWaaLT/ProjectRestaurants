package com.orioninc.ProjectRestaurants.DTO.product;

//public record ProductWarehouseDTO(String productName,
//                                  Float productPrice,
//                                  Float productBalance,
//                                  Long restaurant,
//                                  Integer productExpirable) {
//}

public record ProductWhDTO(Long id,
                           String productName,
                           Float productPrice,
                           Float productBalance,
                           Long restaurant,
                           Integer productExpirable) {
}
