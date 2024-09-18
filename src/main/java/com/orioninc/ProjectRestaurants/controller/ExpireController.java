package com.orioninc.ProjectRestaurants.controller;

import com.orioninc.ProjectRestaurants.dto.expire.ExpireRequestDto;
import com.orioninc.ProjectRestaurants.dto.expire.ExpireResponseDto;

import com.orioninc.ProjectRestaurants.model.Expire;
import com.orioninc.ProjectRestaurants.service.ExpireService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products-expire")
@AllArgsConstructor
public class ExpireController {

    private final ExpireService expireService;

    @PreAuthorize("hasPermission(#id, 'Expire', 'read')")
    @GetMapping
    public ResponseEntity<List<ExpireResponseDto>> getAllProductsExpire() {
        return new ResponseEntity<>(expireService.getAllProductExpires(), HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#id, 'Expire', 'read')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ExpireResponseDto> getProductExpireById(@PathVariable Long id) {
        return new ResponseEntity<>(expireService.getProductExpireById(id), HttpStatus.OK);
    }

//    // TODO Save
//
//    @PreAuthorize("hasPermission(#id, 'Expire', 'create')")
//    @PostMapping
//    public Expire saveProductExpire(@Valid @RequestBody ExpireRequestDTO expireRequestDTO) {
//        Expire expire = ExpireMapper.INSTANCE.expireResponseDtoToExpire(expireRequestDTO);
//
//        return expireService.saveProductExpire(expire);
//    }

    @PreAuthorize("hasPermission(#id, 'Expire', 'update')")
    @PutMapping
    public ResponseEntity<Expire> updateProductExpire(@Valid @RequestBody ExpireRequestDto expireRequestDTO) {
        return new ResponseEntity<>(expireService.updateProductExpire(expireRequestDTO), HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#id, 'Expire', 'delete')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Deleted successfully!")
    public void deleteProductExpire(@PathVariable Long id) {
        expireService.deleteProductExpire(id);
    }

}
