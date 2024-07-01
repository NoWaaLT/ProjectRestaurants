package com.orioninc.ProjectRestaurants.service.implementation;

import com.orioninc.ProjectRestaurants.DTO.menu.MenuResponseDTO;
import com.orioninc.ProjectRestaurants.DTO.menu.MenuResponseDTOMapper;
import com.orioninc.ProjectRestaurants.DTO.menu.MenuRequestDTO;
import com.orioninc.ProjectRestaurants.DTO.menu.MenuRequestDTOMapper;
import com.orioninc.ProjectRestaurants.exceptions.MenuNotFoundException;
import com.orioninc.ProjectRestaurants.model.Menu;
import com.orioninc.ProjectRestaurants.repository.MenuRepository;
import com.orioninc.ProjectRestaurants.service.MenuService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuResponseDTOMapper menuResponseDTOMapper;
    private final MenuRequestDTOMapper menuRequestDTOMapper;

    @Override
    public List<MenuResponseDTO> getAllMenus() {

        return menuRepository.findAll()
                .stream()
                .map(menuResponseDTOMapper)
                .toList();
    }

    @Override
    public List<MenuResponseDTO> getAllMenusByRestaurant(Long id) {

        return menuRepository.findAll()
                .stream()
                .filter((Menu menu) -> menu.getRestaurant().getId().equals(id))
                .map(menuResponseDTOMapper)
                .toList();
    }

    @Override
    public MenuResponseDTO getMenuById(Long id) {

        return menuResponseDTOMapper.apply(menuRepository.findById(id).orElseThrow(() ->
                new MenuNotFoundException("Menu not found.")));
    }

    @Override
    public Menu saveMenu(MenuRequestDTO menuRequestDTO) {
        return menuRepository.save(menuRequestDTOMapper.apply(menuRequestDTO));
    }

    @Override
    public Menu updateMenu(MenuRequestDTO menuRequestDTO) {
        Menu menuToUpdate = menuRequestDTOMapper.apply(menuRequestDTO);
        Menu existingMenu = menuRepository.findById(menuToUpdate.getId())
                .orElseThrow(() -> new MenuNotFoundException("Menu not found."));

        existingMenu.setMenuName(menuToUpdate.getMenuName());
        existingMenu.setRestaurant(menuToUpdate.getRestaurant());
        existingMenu.setRestaurantName(menuToUpdate.getRestaurantName());


        return existingMenu;
    }

    @Override
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}
