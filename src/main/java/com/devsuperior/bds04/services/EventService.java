package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.dto.mapper.EventMapper;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private CityService cityService;

  public List<EventDTO> findAllEvents() {
    final List<Event> list = eventRepository.findAll();

    return list.stream().map(EventMapper::toDto).collect(Collectors.toList());
  }

  public EventDTO saveEvent(final EventDTO eventDTO) {
    final City city = cityService.findCityById(eventDTO.getCityId());
    final Event event = eventRepository.save(EventMapper.toEntity(eventDTO, city));

    return EventMapper.toDto(event);
  }
}
