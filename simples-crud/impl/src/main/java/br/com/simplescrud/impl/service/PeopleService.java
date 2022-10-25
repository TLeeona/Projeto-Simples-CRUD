package br.com.simplescrud.impl.service;

import br.com.simplescrud.impl.dtos.PeopleDto;
import br.com.simplescrud.impl.model.PeopleModel;
import br.com.simplescrud.impl.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PeopleService {

    final PeopleRepository peopleRepository;

    @Transactional
    public ResponseEntity<Object> savePerson(PeopleDto peopleDto) {
        if (peopleRepository.existsByEmail(peopleDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use!");
        }
        var saveNewPerson = new PeopleModel();
        BeanUtils.copyProperties(peopleDto, saveNewPerson);
        saveNewPerson.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(peopleRepository.save(saveNewPerson));
    }

    public ResponseEntity<Page<PeopleModel>> getAllPeople(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(peopleRepository.findAll(pageable));
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
    @Transactional
    public ResponseEntity<Object> updatePerson(UUID id, PeopleDto peopleDto) {
        Optional<PeopleModel> peopleModelOptional = peopleRepository.findById(id);

        if (!peopleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.");
        }
        var updatePerson = peopleModelOptional.get();
        updatePerson.setCompleteName(peopleDto.getCompleteName());
        updatePerson.setBirthDate(peopleDto.getBirthDate());
        updatePerson.setEmail(peopleDto.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(peopleRepository.save(updatePerson));

        //     var updatePerson = peopleModelOptional.get();
        //     BeanUtils.copyProperties(peopleDto, updatePerson);
        //     updatePerson.setId(peopleModelOptional.get().getId());
        //     updatePerson.setRegistrationDate(peopleModelOptional.get().getRegistrationDate());
        //     return ResponseEntity.status(HttpStatus.OK).body(peopleRepository.save(updatePerson));

        //Optei por setar cada campo manualmente, pois são poucos atributos e garante que o ID e o RegistrationDate
        //permanecam os mesmos.
        //Também optei por deixar o segundo feito, dentro dos comentários, para que caso haja a necessidade de aumentar
        //a quantidade de atributos, o código está pronto para uso.

    }

}
