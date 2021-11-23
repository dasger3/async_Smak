package Voronin.MicroService.Lab1.Models;

import Voronin.MicroService.Lab1.Models.Enums.Range;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TTC {

    private Range range;
    private Double sightingRange;
    private boolean hasClip;
    private boolean hasOptics;
}
