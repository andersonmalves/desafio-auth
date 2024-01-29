package com.devsuperior.bds04.controllers;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
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
@RequestMapping("/events")
public class EventController {

  @Autowired
  private EventService eventService;

  @GetMapping
  public ResponseEntity<List<EventDTO>> finalAllEvents() {
    final List<EventDTO> list = eventService.findAllEvents();
    return ResponseEntity.ok().body(list);
  }

  @PostMapping
  public ResponseEntity<EventDTO> saveEvent(@Valid @RequestBody EventDTO eventDTO) {
    final EventDTO event = eventService.saveEvent(eventDTO);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(event.getId())
        .toUri();

    return ResponseEntity.created(uri).body(event);
  }
}
