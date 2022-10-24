package br.com.simplescrud.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PeopleDto {

    @NotBlank
    private String completeName;
    @NotBlank
    private LocalDate birthDate;
    @NotBlank
    private String email;

}
