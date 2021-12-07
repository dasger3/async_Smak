package Galushko.MicroService.Lab1.Models;

import Galushko.MicroService.Lab1.Models.Enums.Handy;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Knife {

    @XmlAttribute
    private Integer id;
    private String type;
    private Handy handy;
    private String origin;
    private boolean value;
    private Visual Visual;
}
