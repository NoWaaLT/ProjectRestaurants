package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.DTO.dish.DishDTO;
import com.orioninc.ProjectRestaurants.DTO.dish.DishRequestDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.dish.DishResponseDTOMapper;
import com.orioninc.ProjectRestaurants.exceptions.DishNotFoundException;
import com.orioninc.ProjectRestaurants.model.Dish;
import com.orioninc.ProjectRestaurants.repository.DishRepository;
import com.orioninc.ProjectRestaurants.service.DishService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final DishResponseDTOMapper dishResponseDTOMapper;
    private final DishRequestDTOMapper dishRequestDTOMapper;

    @Override
    public List<DishDTO> getAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(dishResponseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<DishDTO> getAllDishesByMenuId(Long id) {
        return dishRepository.findAll()
                .stream()
                .filter((Dish dish) -> dish.getMenu().getId().equals(id))
                .map(dishResponseDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElseThrow(() ->
                new DishNotFoundException("Dish is not found"));
    }

    @Override
    public Dish saveDish(DishDTO dishDTO) {
        return dishRepository.save(dishRequestDTOMapper.apply(dishDTO));
    }

    @Override
    public Dish updateDish(DishDTO dishDTO) {
        Dish dishToUpdate = dishRequestDTOMapper.apply(dishDTO);

        Dish existingDish = dishRepository.findById(dishToUpdate.getId()).orElseThrow(() ->
                new DishNotFoundException("Dish not found"));

        existingDish.setId(dishToUpdate.getId());
        existingDish.setDishName(dishToUpdate.getDishName());
        existingDish.setDishPrice(dishToUpdate.getDishPrice());
        existingDish.setMenu(dishToUpdate.getMenu());

        return existingDish;
    }

    @Override
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }
}
