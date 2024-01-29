package com.devsuperior.bds04.dto.mapper;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;

public class CityMapper {

  public static CityDTO toDTO(final City city) {
    return CityDTO.builder()
        .id(city.getId())
        .name(city.getName())
        .build();
  }

  public static City toEntity(final CityDTO cityDTO) {
    return City.builder()
        .name(cityDTO.getName())
        .build();
  }
}
