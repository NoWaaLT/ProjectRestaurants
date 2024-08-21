package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.dto.dish.DishDto;
import com.orioninc.ProjectRestaurants.dto.dish.DishMapper;
import com.orioninc.ProjectRestaurants.exceptions.DishNotFoundException;
import com.orioninc.ProjectRestaurants.exceptions.MenuNotFoundException;
import com.orioninc.ProjectRestaurants.model.Dish;
import com.orioninc.ProjectRestaurants.repository.DishRepository;
import com.orioninc.ProjectRestaurants.repository.MenuRepository;
import com.orioninc.ProjectRestaurants.service.DishService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static com.orioninc.ProjectRestaurants.enums.AppText.*;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

  private final DishRepository dishRepository;
  private final MenuRepository menuRepository;

  @Transactional(readOnly = true)
  @Override
  public List<DishDto> getAllDishes() {
    return dishRepository.findAll().stream().map(DishMapper.INSTANCE::dishToDishDto).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public List<DishDto> getAllDishesByMenuId(Long id) {
    boolean menuExists = menuRepository.existsById(id);

    if (!menuExists) {
      throw new MenuNotFoundException(MENU_BY_ID_NOT_FOUND, id);
    }

    Collection<Dish> dishList = dishRepository.findAllByMenuId(id);

    if (dishList.isEmpty()) {
      throw new DishNotFoundException(DISH_BY_MENU_ID_NOT_FOUND, id);
    }

    return dishList.stream().map(DishMapper.INSTANCE::dishToDishDto).toList();
  }

  @Transactional(readOnly = true)
  @Override
  public DishDto getDishById(Long id) {
    return dishRepository
        .findById(id).map(DishMapper.INSTANCE::dishToDishDto)
        .orElseThrow(() -> new DishNotFoundException(DISH_BY_ID_NOT_FOUND, id));
  }

  @Transactional
  @Override
  public DishDto saveDish(DishDto dishDTO) {
    dishRepository.save(DishMapper.INSTANCE.dishDtoToDish(dishDTO));
    return dishDTO;
  }

  @Transactional(isolation = Isolation.READ_COMMITTED)
  @Override
  public Dish updateDish(DishDto dishDTO) {
    Dish dishToUpdate = DishMapper.INSTANCE.dishDtoToDish(dishDTO);

    Dish existingDish =
        dishRepository
            .findById(dishToUpdate.getId())
            .orElseThrow(
                () -> new DishNotFoundException(DISH_BY_ID_NOT_FOUND, dishToUpdate.getId()));

    existingDish.setId(dishToUpdate.getId());
    existingDish.setDishName(dishToUpdate.getDishName());
    existingDish.setDishPrice(dishToUpdate.getDishPrice());
    existingDish.setMenu(dishToUpdate.getMenu());

    return existingDish;
  }

  @Override
  @Transactional
  public void deleteDish(Long id) {
    dishRepository.deleteById(id);
  }
}
