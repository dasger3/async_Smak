package Galushko.MicroService.Lab1.Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="knives")
public class Knives {
    @XmlElement(name="knife")
    private List<Knife> knives;

    public List<Knife> getKnives() {
        return knives;
    }
}
