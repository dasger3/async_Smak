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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ParserXpath {
    public List<Computer> parse(File file) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // включаем поддержку пространства имен XML
        builderFactory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc;
        try {
            builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(file);

            // Создаем объект XPathFactory
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Получаем экзмепляр XPath для создания
            // XPathExpression выражений
            XPath xpath = xpathFactory.newXPath();

            //XPathExpression xPathExpression = xpath.compile("/computers/computer[@id<3]");
            XPathExpression xPathExpression = xpath.compile("/computers/computer");
            // получаем список всех тегов, которые отвечают условию
            NodeList nodes = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            // проходим по списку и получаем значение с помощью getNodeValue()
            List<Computer> computers = new LinkedList<>();

            for (int temp = 0; temp < nodes.getLength(); temp++) {

                Node node = nodes.item(temp);

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
                        Volos.MicroService.Lab1.Models.Type type = new Type();

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
