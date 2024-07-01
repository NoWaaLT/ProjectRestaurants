package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.DTO.expire.ExpireRequestDTO;
import com.orioninc.ProjectRestaurants.DTO.expire.ExpireRequestDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.expire.ExpireResponseDTO;

import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.service.ProductExpireService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class ExpireController {

    private final ProductExpireService productExpireService;
    private final ExpireRequestDTOMapper expireRequestDTOMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "products/expires/get")
    public List<ExpireResponseDTO> getAllProductsExpire() {
        return productExpireService.getAllProductExpires();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "products/expires/get/{id}")
    public ExpireResponseDTO getProductExpireById(@PathVariable Long id) {
        return productExpireService.getProductExpireById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "products/expires/save")
    public Expire saveProductExpire(@RequestBody ExpireRequestDTO expireRequestDTO) {
        Expire expire = expireRequestDTOMapper.apply(expireRequestDTO);

        return productExpireService.saveProductExpire(expire);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "products/expire/")
    public Expire updateProductExpire(@RequestBody ExpireRequestDTO expireRequestDTO) {
        return productExpireService.updateProductExpire(expireRequestDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "products/expire/delete/{id}")
    public void deleteProductExpire(@PathVariable Long id) {
        productExpireService.deleteProductExpire(id);
    }

}
