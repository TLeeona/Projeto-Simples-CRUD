package br.com.simplescrud.service;

import br.com.simplescrud.dtos.PeopleDto;
import br.com.simplescrud.model.PeopleModel;
import br.com.simplescrud.repository.PeopleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class PeopleService {

    final PeopleRepository peopleRepository;
@Transactional
    public ResponseEntity<Object> savePerson (PeopleDto peopleDto){
    if (peopleRepository.existsByEmail(peopleDto.getEmail())){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use!");
    }
    var saveNewPerson = new PeopleModel();
    BeanUtils.copyProperties(peopleDto, saveNewPerson);
    saveNewPerson.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
    return ResponseEntity.status(HttpStatus.CREATED).body(peopleRepository.save(saveNewPerson));
    }

    public ResponseEntity<List<PeopleModel>> getAllPeople() {
        return ResponseEntity.status(HttpStatus.OK).body(peopleRepository.findAll());
    }

    public ResponseEntity<Object> getOnePerson(UUID id) {
        Optional<PeopleModel> peopleModelOptional = peopleRepository.findById(id);
        if (!peopleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peopleModelOptional.get());
    }

    @Transactional
    public ResponseEntity<Object> deletePerson(UUID id) {
        Optional<PeopleModel> peopleModelOptional = peopleRepository.findById(id);
        if (!peopleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }
        peopleRepository.delete(peopleModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully.");
    }
}
