package com.devsuperior.bds04.controllers.exceptions;

import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StandardError {

  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}
