package com.orioninc.ProjectRestaurants.dto.menu;

import com.orioninc.ProjectRestaurants.model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    MenuResponseDto menuToMenuResponseDto(Menu menu);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "restaurant", target = "restaurant.id")
    @Mapping(target = "dishList", ignore = true)
    Menu menuRequestDtoToMenu(MenuRequestDto menuRequestDTO);
}
