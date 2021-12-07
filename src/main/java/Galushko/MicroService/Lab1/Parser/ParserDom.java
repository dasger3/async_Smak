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
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ParserDom {

    public List<Knife> parse(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("knife");

            List<Knife> knives = new LinkedList<>();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node node = nList.item(temp);

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
                        Visual visual = new Visual();


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
