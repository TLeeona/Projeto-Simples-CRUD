package br.com.simplescrud.impl.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class PeopleDto {

    @NotBlank
    private String completeName;
    private LocalDate birthDate;
    @NotBlank
    private String email;

}
