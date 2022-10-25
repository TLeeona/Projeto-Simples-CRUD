package br.com.simplescrud.impl.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TABLE_OF_PEOPLES")
public class PeopleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = true, length = 70)
    private LocalDateTime registrationDate;
    @Column(nullable = false, length = 100)
    private String completeName;
    @Column(nullable = false, length = 100)
    private LocalDate birthDate;
    @Column(nullable = false, length = 100)
    private String email;

}
