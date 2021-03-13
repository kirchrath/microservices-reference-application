package com.anitechcs.bookservice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public abstract class ConnectHandler {
    <T> Optional<T> readValue(ObjectMapper objectMapper, String value, Class<T> tClass) {
        try {
            return Optional.ofNullable(objectMapper.readValue(value, tClass));
        } catch (JsonProcessingException e) {
            log.error("Error while reading object", e);
            return Optional.empty();
        }
    }
}
