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
@RequestMapping("/api/products-expire")
@AllArgsConstructor
public class ExpireController {

    private final ProductExpireService productExpireService;
    private final ExpireRequestDTOMapper expireRequestDTOMapper;

    @PreAuthorize("hasPermission(#id, 'Expire', 'read')")
    @GetMapping
    public List<ExpireResponseDTO> getAllProductsExpire() {
        return productExpireService.getAllProductExpires();
    }

    @PreAuthorize("hasPermission(#id, 'Expire', 'read')")
    @GetMapping(value = "/{id}")
    public ExpireResponseDTO getProductExpireById(@PathVariable Long id) {
        return productExpireService.getProductExpireById(id);
    }

    @PreAuthorize("hasPermission(#id, 'Expire', 'create')")
    @PostMapping
    public Expire saveProductExpire(@RequestBody ExpireRequestDTO expireRequestDTO) {
        Expire expire = expireRequestDTOMapper.apply(expireRequestDTO);

        return productExpireService.saveProductExpire(expire);
    }

    // TODO consider to make id by id in URI

    @PreAuthorize("hasPermission(#id, 'Expire', 'update')")
    @PutMapping(value = "products/expire/")
    public Expire updateProductExpire(@RequestBody ExpireRequestDTO expireRequestDTO) {
        return productExpireService.updateProductExpire(expireRequestDTO);
    }

    @PreAuthorize("hasPermission(#id, 'Expire', 'delete')")
    @DeleteMapping(value = "/{id}")
    public void deleteProductExpire(@PathVariable Long id) {
        productExpireService.deleteProductExpire(id);
    }

}
