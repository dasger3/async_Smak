package Galushko.MicroService.Lab1.Parser;

import Galushko.MicroService.Lab1.Models.Enums.Handy;
import Galushko.MicroService.Lab1.Models.Knife;
import Galushko.MicroService.Lab1.Models.Visual;
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
    public List<Knife> parse(File file) {
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

            //XPathExpression xPathExpression = xpath.compile("/knives/knife[@id<3]");
            XPathExpression xPathExpression = xpath.compile("/knives/knife");
            // получаем список всех тегов, которые отвечают условию
            NodeList nodes = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            // проходим по списку и получаем значение с помощью getNodeValue()
            List<Knife> knives = new LinkedList<>();

            for (int temp = 0; temp < nodes.getLength(); temp++) {

                Node node = nodes.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    Knife knife = new Knife();

                    knife.setId(Integer.parseInt(element.getAttribute("id")));
                    knife.setType(getValue(element, "type"));
                    knife.setHandy(Handy.valueOf(getValue(element, "handy")));
                    knife.setOrigin(getValue(element, "origin"));
                    knife.setValue(Boolean.parseBoolean(getValue(element, "value")));

                    Node Visual = element.getElementsByTagName("Visual").item(0);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element elementVisual = (Element) Visual;
                        Galushko.MicroService.Lab1.Models.Visual visual = new Visual();


                        visual.setLengthOfKnife(Double.parseDouble(getValue(elementVisual, "lengthOfKnife")));
                        visual.setWidthOfKnife(Double.parseDouble(getValue(elementVisual, "widthOfKnife")));
                        visual.setMaterial(getValue(element, "material"));
                        visual.setHandle(getValue(element, "handle"));
                        visual.setBloodstream(Boolean.parseBoolean(getValue(elementVisual, "bloodstream")));

                        knife.setVisual(visual);
                    }

                    knives.add(knife);
                }
            }

            return knives;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String getValue(Element element, String nameOfValue) {
        return element.getElementsByTagName(nameOfValue).item(0).getTextContent();
    }
}
