package Voronin.MicroService.Lab1.Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="guns")
public class Guns {
    @XmlElement(name="gun")
    private List<Gun> guns;

    public List<Gun> getGuns() {
        return guns;
    }
}
