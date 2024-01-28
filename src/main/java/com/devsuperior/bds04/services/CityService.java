package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  public List<CityDTO> findAllCities() {
    final List<City> list = cityRepository.findAll(Sort.by("name"));
    return list.stream()
        .map(city -> CityDTO.builder()
            .id(city.getId())
            .name(city.getName())
            .build())
        .collect(Collectors.toList());
  }

  @Transactional
  public CityDTO saveCity(CityDTO cityDTO) {
    final City city = cityRepository.save(City.builder()
        .name(cityDTO.getName())
        .build());

    return CityDTO.builder()
        .id(city.getId())
        .name(city.getName())
        .build();
  }
}
