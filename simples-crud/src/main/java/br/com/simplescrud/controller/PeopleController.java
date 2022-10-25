package br.com.simplescrud.controller;

import br.com.simplescrud.dtos.PeopleDto;
import br.com.simplescrud.model.PeopleModel;
import br.com.simplescrud.service.PeopleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/simples-crud")
@AllArgsConstructor
public class PeopleController {

    final PeopleService peopleService;

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody @Valid PeopleDto peopleDto) {
        return peopleService.savePerson(peopleDto);
    }

    @GetMapping
    public ResponseEntity<List<PeopleModel>> getAllPeople() {
        return peopleService.getAllPeople();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> saveOnePerson(@PathVariable(value = "id") UUID id) {
        return peopleService.getOnePerson(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value = "id") UUID id) {
        return peopleService.deletePerson(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid PeopleDto peopleDto) {
        return peopleService.updatePerson(id, peopleDto);
    }

}