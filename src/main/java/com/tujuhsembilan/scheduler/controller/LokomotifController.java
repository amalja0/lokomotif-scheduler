package com.tujuhsembilan.scheduler.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.tujuhsembilan.scheduler.model.Lokomotif;
import com.tujuhsembilan.scheduler.model.dto.LokomotifDto;
import com.tujuhsembilan.scheduler.repository.LokomotifRepository;
import com.tujuhsembilan.scheduler.service.KafkaProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/lokomotif")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LokomotifController {

    private final ModelMapper modelMapper;

    private final LokomotifRepository lokomotifRepository;

    private final KafkaProducer kafkaProducer;

    Faker faker = new Faker();

    @GetMapping("/create-data-lokomotif")
    public ResponseEntity<?> createLokomotifData() {
        LocalDateTime randomDate =  LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");

        String formattedDateTime = randomDate.format(formatter);


        Lokomotif lokomotif = Lokomotif.builder()
            .kodeLoko(faker.number().digits(5))
            .namaLoko(faker.address().country())
            .dimensiLoko(faker.number().digits(2))
            .status(faker.number().digits(1))
            .createdDate(formattedDateTime)
            .build();
        
        kafkaProducer.sendMessage(lokomotif);
        
        var savedModel = lokomotifRepository.save(lokomotif);

        var responseBodyDto = modelMapper.map(savedModel, LokomotifDto.class);

        log.info("Generated Random Lokomotif Data: {}", responseBodyDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(RepresentationModel.of(responseBodyDto));

    }
}
