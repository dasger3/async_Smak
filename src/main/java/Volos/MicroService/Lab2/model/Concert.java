package Volos.MicroService.Lab2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "performances")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConcert;

    private String name;

    private Date date;

    @OneToMany
    private List<Actor> actorList;

    @OneToMany
    private List<Ticket> ticketList;
}
