package com.example.order.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Constants {
  public static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    MAPPER.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
    MAPPER.registerModule(new JavaTimeModule());
  }
}
