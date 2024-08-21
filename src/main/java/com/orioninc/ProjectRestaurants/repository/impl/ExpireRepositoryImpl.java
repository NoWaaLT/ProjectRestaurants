//package com.orioninc.ProjectRestaurants.repository.impl;
//
//
//import com.orioninc.ProjectRestaurants.DTO.expire.ExpireResponseDTO;
//import com.orioninc.ProjectRestaurants.DTO.expire.ExpireResponseDTOMapper;
//import com.orioninc.ProjectRestaurants.model.Expire;
//import com.orioninc.ProjectRestaurants.repository.ExpireRepository;
//
//import jakarta.persistence.EntityManager;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class ExpireRepositoryImpl extends BaseRepositoryImpl<Expire, Long> implements ExpireRepository {
//
//    ExpireResponseDTOMapper expireResponseDTOMapper;
//
//    protected ExpireRepositoryImpl(Class<Expire> domainClass, EntityManager entityManager) {
//        super(domainClass, entityManager);
//    }
//
////    @Override
////    public Optional<List<ExpireResponseDTO>> findExpiresByProductId(Long id) {
////        return Optional.of(jpaQueryFactory.selectFrom(expire)
////                .where(expire.product.id.eq(id))
////                .stream()
////                .map(expireResponseDTOMapper)
////                .toList());
////    }
////
////    @Override
////    public void editExpireData(float quantity, boolean status, long id) {
////        jpaQueryFactory.update(expire)
////                .where(expire.id.eq(id))
////                .set(expire.batchQuantity, quantity)
////                .set(expire.removedProduct, status)
////                .execute();
////    }
//}
