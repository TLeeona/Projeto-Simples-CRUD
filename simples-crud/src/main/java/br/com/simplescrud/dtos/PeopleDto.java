package br.com.simplescrud.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PeopleDto {

    @NotBlank
    private String completeName;
    private LocalDate birthDate;
    @NotBlank
    private String email;

}
