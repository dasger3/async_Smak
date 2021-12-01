package Volos.MicroService.Lab1.Models;

import Volos.MicroService.Lab1.Models.Enums.Ports;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Type {

    private boolean isPeripheral;
    private Double energyConsumption;
    private boolean hasCooler;
    private Ports Ports;
}
