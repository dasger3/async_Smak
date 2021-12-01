package Volos.MicroService.Lab2.model;

import Volos.MicroService.Lab2.model.enums.TypeOfTicket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private String place;

    @Enumerated(EnumType.STRING)
    private TypeOfTicket typeOfTicket;

    private Double price;

    private Date date;
}
