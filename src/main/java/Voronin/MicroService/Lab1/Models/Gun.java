package Voronin.MicroService.Lab1.Models;

import Voronin.MicroService.Lab1.Models.Enums.Handy;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Gun {

    @XmlAttribute
    private Integer id;
    private String model;
    private Handy handy;
    private String origin;
    private String material;
    private TTC TTC;
}
