package br.com.simplescrud.controller;

import br.com.simplescrud.dtos.PeopleDto;
import br.com.simplescrud.service.PeopleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simples-crud")
@AllArgsConstructor
public class PeopleController {

    final PeopleService peopleService;

    @PostMapping
    public ResponseEntity<Object> savePerson (@RequestBody @Valid PeopleDto peopleDto){
        return peopleService.savePerson(peopleDto);
    }

    @GetMapping
    public ResponseEntity<List<PeopleModel>> getAllPeople() {
        return peopleService.getAllPeople();
    }
