package com.devsuperior.bds04.dto.mapper;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;

public class EventMapper {

  public static EventDTO toDto(final Event event) {
    return EventDTO.builder()
        .id(event.getId())
        .name(event.getName())
        .url(event.getUrl())
        .date(event.getDate())
        .cityId(event.getCity().getId())
        .build();
  }

  public static Event toEntity(final EventDTO eventDTO, final City city) {
    return Event.builder()
        .id(eventDTO.getId())
        .name(eventDTO.getName())
        .city(city)
        .date(eventDTO.getDate())
        .url(eventDTO.getUrl())
        .build();
  }
}
