package Galushko.MicroService.Lab1.Models;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Visual {

    private Double lengthOfKnife;
    private Double widthOfKnife;
    private String material;
    private String handle;
    private boolean bloodstream;
}
