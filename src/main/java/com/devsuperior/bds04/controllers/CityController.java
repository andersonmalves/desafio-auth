package com.devsuperior.bds04.controllers;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/cities")
public class CityController {

  @Autowired
  private CityService cityService;

  @GetMapping
  public ResponseEntity<List<CityDTO>> findAllCities() {
    final List<CityDTO> list = cityService.findAllCities();
    return ResponseEntity.ok().body(list);
  }

  @PostMapping
  public ResponseEntity<CityDTO> saveCity(@Valid @RequestBody CityDTO cityDTO) {
    final CityDTO city = cityService.saveCity(cityDTO);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(city.getId())
        .toUri();

    return ResponseEntity.created(uri).body(city);
  }
}
