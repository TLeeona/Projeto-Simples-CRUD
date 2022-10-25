package br.com.simplescrud.controller;

import br.com.simplescrud.impl.service.PeopleService;
import br.com.simplescrud.impl.dtos.PeopleDto;
import br.com.simplescrud.impl.model.PeopleModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Save Person",
            produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Save person."),
            @ApiResponse(code = 404, message = "Conflict: Email is already in use!"),
    })
    public ResponseEntity<Object> savePerson(@RequestBody @Valid PeopleDto peopleDto) {
        return peopleService.savePerson(peopleDto);
    }

    @GetMapping
    @ApiOperation(value = "Get All People",
            produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return all people."),
    })
    public ResponseEntity<Page<PeopleModel>> getAllPeople(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return peopleService.getAllPeople(pageable);
    }

    @GetMapping("/{Id}")
    @ApiOperation(value = "Get One Person",
            produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return person."),
            @ApiResponse(code = 404, message = "Person not found."),
    })
    public ResponseEntity<Object> getOnePerson(@PathVariable(value = "id") UUID id) {
        return peopleService.getOnePerson(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Person",
            produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Delete person."),
            @ApiResponse(code = 404, message = "Person not found."),
    })
    public ResponseEntity<Object> deletePerson(@PathVariable(value = "id") UUID id) {
        return peopleService.deletePerson(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Person",
            produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Update person."),
            @ApiResponse(code = 404, message = "Person not found."),
    })
    public ResponseEntity<Object> updatePerson(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid PeopleDto peopleDto) {
        return peopleService.updatePerson(id, peopleDto);
    }

}
