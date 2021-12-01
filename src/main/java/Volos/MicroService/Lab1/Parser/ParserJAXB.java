package Volos.MicroService.Lab1.Parser;

import Volos.MicroService.Lab1.Models.Computer;
import Volos.MicroService.Lab1.Models.Computers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ParserJAXB {
    public List<Computer> parse(File file) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Computers.class);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Computers computersResult = (Computers) unmarshaller.unmarshal(file);

            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.marshal(computersResult, System.out);

            return computersResult.getComputers();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
