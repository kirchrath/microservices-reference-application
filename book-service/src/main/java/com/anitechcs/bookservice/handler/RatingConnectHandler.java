package com.anitechcs.bookservice.handler;

import com.anitechcs.bookservice.dto.BookRatingConnectDto;
import com.anitechcs.bookservice.service.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RatingConnectHandler extends ConnectHandler {
    private final ObjectMapper objectMapper;
    private final BookServiceImpl bookApplicationService;

    public RatingConnectHandler(ObjectMapper objectMapper, BookServiceImpl bookApplicationService) {
        this.objectMapper = objectMapper;
        this.bookApplicationService = bookApplicationService;
    }

    @KafkaListener(topics = "${com.anitechcs.bookservice.topic.connect.rating}")
    public void handle(String message) {
        Optional<BookRatingConnectDto> optionalDto = readValue(objectMapper, message, BookRatingConnectDto.class);
        optionalDto.ifPresent(it -> bookApplicationService.updateRating(it.getPayload()));
    }
}
