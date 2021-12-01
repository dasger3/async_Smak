package Volos.MicroService.Lab2.model;

import Volos.MicroService.Lab2.model.enums.TypeOfActor;
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
@Table (name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private TypeOfActor typeOfActor;
}
