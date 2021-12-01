package Volos.MicroService.Lab1.Models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="computers")
public class Computers {
    @XmlElement(name="computer")
    private List<Computer> computers;

    public List<Computer> getComputers() {
        return computers;
    }
}
