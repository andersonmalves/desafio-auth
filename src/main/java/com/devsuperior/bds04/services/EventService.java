package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  public List<EventDTO> findAllEvents() {
    final List<Event> list = eventRepository.findAll();

    return list.stream().map(event -> EventDTO.builder()
        .id(event.getId())
        .name(event.getName())
        .url(event.getUrl())
        .date(event.getDate())
        .cityId(event.getCity().getId())
        .build()).collect(Collectors.toList());
  }
}
