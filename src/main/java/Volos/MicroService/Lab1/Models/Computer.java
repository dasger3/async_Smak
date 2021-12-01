package Volos.MicroService.Lab1.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Computer {

    @XmlAttribute
    private Integer id;
    private String name;
    private String origin;
    private Double price;
    private Type Type;
    private boolean critical;
}
