package br.com.simplescrud.controller;

import br.com.simplescrud.impl.service.PeopleService;
import br.com.simplescrud.impl.dtos.PeopleDto;
import br.com.simplescrud.impl.model.PeopleModel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/Person")
@AllArgsConstructor
public class PeopleController {

    final PeopleService peopleService;

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody @Valid PeopleDto peopleDto) {
        return peopleService.savePerson(peopleDto);
    }

    @GetMapping
    public ResponseEntity<Page<PeopleModel>> getAllPeople(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return peopleService.getAllPeople(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePerson(@PathVariable(value = "id") UUID id) {
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
