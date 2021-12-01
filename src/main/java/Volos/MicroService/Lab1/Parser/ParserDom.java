package Volos.MicroService.Lab1.Parser;

import Volos.MicroService.Lab1.Models.Computer;
import Volos.MicroService.Lab1.Models.Enums.Ports;
import Volos.MicroService.Lab1.Models.Type;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ParserDom {

    public List<Computer> parse(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("computer");

            List<Computer> computers = new LinkedList<>();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node node = nList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    Computer computer = new Computer();

                    computer.setId(Integer.parseInt(element.getAttribute("id")));
                    computer.setName(getValue(element, "name"));
                    computer.setOrigin(getValue(element, "origin"));
                    computer.setPrice(Double.parseDouble(getValue(element, "price")));
                    computer.setCritical(Boolean.parseBoolean(getValue(element, "critical")));

                    Node Type = element.getElementsByTagName("Type").item(0);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element elementType = (Element) Type;
                        Type type = new Type();

                        type.setPeripheral(Boolean.parseBoolean(getValue(elementType, "isPeripheral")));
                        type.setEnergyConsumption(Double.parseDouble(getValue(element, "energyConsumption")));
                        type.setHasCooler(Boolean.parseBoolean(getValue(elementType, "hasCooler")));
                        type.setPorts(Ports.valueOf(getValue(elementType, "Ports")));

                        computer.setType(type);
                    }

                    computers.add(computer);
                }
            }

            return computers;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getValue(Element element, String nameOfValue) {
        return element.getElementsByTagName(nameOfValue).item(0).getTextContent();
    }
}
