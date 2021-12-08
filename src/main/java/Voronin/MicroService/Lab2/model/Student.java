package Voronin.MicroService.Lab2.model;

import Voronin.MicroService.Lab2.model.enums.TypeOfSpecialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private TypeOfSpecialty typeOfSpecialty;
}
