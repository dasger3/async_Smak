package Galushko.MicroService.Lab1.Parser;


import Galushko.MicroService.Lab1.Models.Knife;
import Galushko.MicroService.Lab1.Models.Knives;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ParserJAXB {
    public List<Knife> parse(File file) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Knives.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Knives knivesResult = (Knives) unmarshaller.unmarshal(file);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            return knivesResult.getKnives();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
