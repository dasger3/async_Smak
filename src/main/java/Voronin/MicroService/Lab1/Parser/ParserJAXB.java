package Voronin.MicroService.Lab1.Parser;

import Voronin.MicroService.Lab1.Models.Gun;
import Voronin.MicroService.Lab1.Models.Guns;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ParserJAXB {
    public List<Gun> parse(File file) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Guns.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Guns gunsResult = (Guns) unmarshaller.unmarshal(file);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.marshal(gunsResult, System.out);

            return gunsResult.getGuns();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
